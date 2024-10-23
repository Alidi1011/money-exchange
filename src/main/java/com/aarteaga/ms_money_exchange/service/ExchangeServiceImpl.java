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
    ExchangeRepository exchangeRepository;
    @Override
    public Flux<Exchange> findAll() {
        return Flux.fromIterable(exchangeRepository.findAll());
    }

    @Override
    public Mono<Exchange> findById(Long id) {
        Optional<Exchange> exchangeExist = exchangeRepository.findById(id);

        if (exchangeExist.isPresent()) {
            return Mono.just(exchangeExist.get());
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Mono<Exchange> create(Exchange exchange) {
        return Mono.just(exchangeRepository.save(exchange));
    }

    @Override
    public Mono<Exchange> update(Exchange exchange, Long id) {

        return this.findById(id).map(c -> {
                    c.setExchangeRate(exchange.getExchangeRate());
                    return exchangeRepository.save(c);
                });
    }

    @Override
    public Mono<Object> delete(Long id) {
        return this.findById(id)
                .map(c -> {
                    exchangeRepository.delete(c);
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Exchange> findByOriginAndDestinyCurrency(String origin, String destiny) {
        return Mono.just(exchangeRepository.findByOriginCurrencyAndDestinyCurrency(origin, destiny));
    }
}
