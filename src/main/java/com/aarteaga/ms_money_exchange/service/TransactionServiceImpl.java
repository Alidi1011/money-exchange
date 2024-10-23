package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.model.Exchange;
import com.aarteaga.ms_money_exchange.model.TransactionExchange;
import com.aarteaga.ms_money_exchange.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ExchangeService exchangeService;

    @Override
    public Flux<TransactionExchange> findAll() {
            return Flux.fromIterable(transactionRepository.findAll());
    }

    @Override
    public Mono<TransactionExchange> create(TransactionExchange transactionExchange) {
        //Get exchange rate
        return exchangeService.findByOriginAndDestinyCurrency(transactionExchange.getOriginCurrency(), transactionExchange.getDestinyCurrency())
                .map(exchange -> {
                    Double exchangeRate = exchange.getExchangeRate();
                    Double finalAmount = transactionExchange.getInitialAmount()/exchangeRate;
                    transactionExchange.setFinalAmount(finalAmount);
                    transactionExchange.setExchangeRate(exchangeRate);
                    transactionRepository.save(transactionExchange);
                    return transactionExchange;
                });
    }
}
