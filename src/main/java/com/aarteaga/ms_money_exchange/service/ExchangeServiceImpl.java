package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.model.Exchange;
import com.aarteaga.ms_money_exchange.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    ExchangeRepository repo;
    @Override
    public Flux<Exchange> findAll() {
        return Flux.fromIterable(repo.findAll());
    }

    @Override
    public Mono<Exchange> create(Exchange exchange) {
        return Mono.just(repo.save(exchange));
    }

    @Override
    public Mono<Exchange> update(Exchange exchange, Long id) {
        Optional<Exchange> exchangeExist = repo.findById(id);

        Exchange exchangeUpdated;

        if(exchangeExist.isPresent()){
            exchangeUpdated = exchangeExist.get();
            exchangeUpdated.setExchangeRate(exchange.getExchangeRate());
            return Mono.just(repo.save(exchangeUpdated));
        }else {
            return null;
        }
    }
}
