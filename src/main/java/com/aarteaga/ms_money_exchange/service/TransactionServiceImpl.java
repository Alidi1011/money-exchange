package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.dto.GorestUserDto;
import com.aarteaga.ms_money_exchange.model.Exchange;
import com.aarteaga.ms_money_exchange.model.TransactionExchange;
import com.aarteaga.ms_money_exchange.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ExchangeService exchangeService;

    @Autowired
    GorestService gorestService;

    @Override
    public Flux<TransactionExchange> findAll() {
            return Flux.fromIterable(transactionRepository.findAll());
    }

    @Override
    public Mono<TransactionExchange> create(TransactionExchange transactionExchange) {
        //Get exchange rate
        return exchangeService.findByOriginAndDestinyCurrency(transactionExchange.getOriginCurrency(), transactionExchange.getDestinyCurrency())
                .map(exchange -> {
                    GorestUserDto gorestUserDto = null;
                    try {
                        gorestUserDto = gorestService.findById(transactionExchange.getUserId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Double exchangeRate = exchange.getExchangeRate();
                    Double finalAmount = transactionExchange.getInitialAmount()/exchangeRate;
                    transactionExchange.setFinalAmount(finalAmount);
                    transactionExchange.setExchangeRate(exchangeRate);
                    transactionExchange.setUsername(gorestUserDto.getName());
                    transactionRepository.save(transactionExchange);
                    return transactionExchange;
                });
    }
}
