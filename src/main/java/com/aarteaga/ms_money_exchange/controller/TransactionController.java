package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.model.Exchange;
import com.aarteaga.ms_money_exchange.model.TransactionExchange;
import com.aarteaga.ms_money_exchange.service.ExchangeService;
import com.aarteaga.ms_money_exchange.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Mono<ResponseEntity<TransactionExchange>> create(@RequestBody TransactionExchange transactionExchange) {
        return transactionService.create(transactionExchange)
                .map(te -> ResponseEntity.ok().body(te));
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<TransactionExchange>>> findAll() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(transactionService.findAll()));
    }
}

