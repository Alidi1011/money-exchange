package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.entity.Exchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeService {
    public Flux<Exchange> findAll();

    public Mono<Exchange> findById(Long id);

    public Mono<Exchange> create(Exchange exchange);

    public Mono<Exchange> update(Exchange exchange, Long id);

    public Mono<Object> delete(Long id);

    Mono<Exchange> findByOriginAndDestinyCurrency(String origin, String destiny);
}
