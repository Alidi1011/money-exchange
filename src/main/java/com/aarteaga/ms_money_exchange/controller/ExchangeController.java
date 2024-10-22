package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.model.Exchange;
import com.aarteaga.ms_money_exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/status")
    public String status() {
        return "Working exchange status";
    }

    /**
     * Get list of Exchanges
     *
     * @author Alisson Arteaga
     * @version 1.0
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<Exchange>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(exchangeService.findAll()));
    }

    @PostMapping
    public Mono<ResponseEntity<Mono<Exchange>>> create(@RequestBody Exchange exchange) {
        return Mono.just(
                ResponseEntity.ok()
                        .body(exchangeService.create(exchange)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Mono<Exchange>>> update(@RequestBody Exchange exchange,
                                                 @PathVariable Long id) {
         return Mono.just(
                ResponseEntity.ok()
                        .body(exchangeService.update(exchange, id)));
    }
}
