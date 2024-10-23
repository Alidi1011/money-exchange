package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.model.Exchange;
import com.aarteaga.ms_money_exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    /**
     * Get list of Exchanges
     *
     * @author Alisson Arteaga
     * @version 1.0
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<Exchange>>> findAll() {
        return Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(exchangeService.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Exchange>> read(@PathVariable Long id) {
        return exchangeService.findById(id).map(customer -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(customer))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Exchange>> create(@RequestBody Exchange exchange) {
        return exchangeService.create(exchange)
                .map(exchangeCreated -> ResponseEntity.ok().body(exchangeCreated));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Exchange>> update(@RequestBody Exchange exchange,
                                                 @PathVariable Long id) {
         return exchangeService.update(exchange, id)
                 .map(exchangeUpdated -> ResponseEntity.ok().body(exchangeUpdated))
                 .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable Long id) {
        return exchangeService.delete(id)
                .map(e -> ResponseEntity.noContent().build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/find")
    public Mono<ResponseEntity<Exchange>> findByOriginDestinyCurrency(@RequestParam String origin,  @RequestParam String destiny) {
        return exchangeService.findByOriginAndDestinyCurrency(origin, destiny)
                .map(exchangeCreated -> ResponseEntity.ok().body(exchangeCreated));
    }
}
