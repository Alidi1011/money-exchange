package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.model.Exchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeService {
    public Flux<Exchange> findAll();

    public Mono<Exchange> create(Exchange exchange);

    public Mono<Exchange> update(Exchange exchange, Long id);
}
