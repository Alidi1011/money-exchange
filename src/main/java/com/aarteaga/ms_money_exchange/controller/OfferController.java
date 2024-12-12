package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.entity.OfferEntity;
import com.aarteaga.ms_money_exchange.model.SaveOfferRequest;
import com.aarteaga.ms_money_exchange.service.OfferService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Tag(name = "Ofertas", description = "Offer API")
@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET = "mySecretKey";

    @GetMapping
    public Mono<ResponseEntity<Flux<OfferEntity>>> findAll() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(offerService.findAll()));
    }

    @PostMapping
    public Mono<ResponseEntity<OfferEntity>> create(@RequestBody SaveOfferRequest saveOfferRequest) {
        return offerService.create(saveOfferRequest)
                .map(te -> ResponseEntity.ok().body(te));
    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<OfferEntity>> update(@RequestBody SaveOfferRequest saveOfferRequest,
                                                    @PathVariable Integer id) {
        return offerService.update(saveOfferRequest, id)
                .map(offerEntityUpdated -> ResponseEntity.ok().body(offerEntityUpdated))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

