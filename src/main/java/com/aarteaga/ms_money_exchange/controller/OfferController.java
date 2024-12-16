package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.model.OfferDetail;
import com.aarteaga.ms_money_exchange.model.OfferRequestSave;
import com.aarteaga.ms_money_exchange.model.OfferResponse;
import com.aarteaga.ms_money_exchange.service.OfferService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@Api(tags = "Offer", description = "Registra y obtiene las ofertas")
@Validated
@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping(value = "/retrieve")
    public Flux<OfferDetail> findAll() {
        return offerService.findAll();
    }

    @PostMapping(value = "/create")
    public Mono<OfferResponse> create(@RequestBody @Valid OfferRequestSave saveOfferRequest) {
        return offerService.create(saveOfferRequest);
    }


    @PutMapping("/update/{id}")
    public Mono<OfferResponse> update(@RequestBody OfferRequestSave saveOfferRequest,
                                      @PathVariable(required = true, name = "id") @Valid Integer id) {
        return offerService.update(saveOfferRequest, id);
    }
}

