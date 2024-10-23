package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.model.TransactionExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    public Flux<TransactionExchange> findAll();
    public Mono<TransactionExchange> create(TransactionExchange transactionExchange);
}
