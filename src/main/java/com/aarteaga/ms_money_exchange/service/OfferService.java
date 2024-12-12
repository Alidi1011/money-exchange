package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.entity.OfferEntity;
import com.aarteaga.ms_money_exchange.model.SaveOfferRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.criteria.CriteriaBuilder;

public interface OfferService {
    public Flux<OfferEntity> findAll();
    public Mono<OfferEntity> create(SaveOfferRequest saveOfferRequest);
    public Mono<OfferEntity> update(SaveOfferRequest saveOfferRequest, Integer id);
}
