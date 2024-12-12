package com.aarteaga.ms_money_exchange.service.impl;

import com.aarteaga.ms_money_exchange.entity.OfferEntity;
import com.aarteaga.ms_money_exchange.model.SaveOfferRequest;
import com.aarteaga.ms_money_exchange.repository.OfferRepository;
import com.aarteaga.ms_money_exchange.service.OfferService;
import com.aarteaga.ms_money_exchange.service.mapper.OfferMapper;
import com.aarteaga.ms_money_exchange.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRepository offerRepository;

    @Override
    public Flux<OfferEntity> findAll() {
        return Flux.fromIterable(offerRepository.findAll());
    }

    @Override
    public Mono<OfferEntity> create(SaveOfferRequest saveOfferRequest) {

        //OfferEntity offerEntity = offerRepository.save(OfferMapper.toOfferEntityCreate(saveOfferRequest));

        return Mono.just(offerRepository.save(OfferMapper.toOfferEntityCreate(saveOfferRequest)));
    }

    @Override
    public Mono<OfferEntity> update(SaveOfferRequest saveOfferRequest, Integer id) {
        Optional<OfferEntity> offerExist = offerRepository.findById(id);

        if (offerExist.isPresent()) {

            OfferEntity offer = offerExist.get();
            /*offer.setProduct(saveOfferRequest.getProduct());
            offer.setCardDescription(saveOfferRequest.getProduct());
            offer.setButtonName(saveOfferRequest.getButtonName());
            offer.setButtonLink(saveOfferRequest.getButtonLink());
            offer.setBackgroundImage(saveOfferRequest.getBackgroundImage());
            offer.setAdditionalImage(saveOfferRequest.getAdditionalImage());
            offer.setModifiedBy(saveOfferRequest.getUser());
            offer.setModificationDate(Utility.getLocalDateTime());*/

            return Mono.just(offerRepository.save(OfferMapper.toOfferEntityUpdate(offerExist.get(), saveOfferRequest)));
        } else {
            return Mono.empty();
        }
    }
}
