package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.model.OfferDetail;
import com.aarteaga.ms_money_exchange.model.OfferRequestSave;
import com.aarteaga.ms_money_exchange.model.OfferResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OfferService {
    Flux<OfferDetail> findAll();
    Mono<OfferResponse> create(OfferRequestSave offerRequestSave);
    Mono<OfferResponse> update(OfferRequestSave offerRequestSave, Integer id);
}
