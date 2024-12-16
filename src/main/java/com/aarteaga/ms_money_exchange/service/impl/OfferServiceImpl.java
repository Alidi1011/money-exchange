package com.aarteaga.ms_money_exchange.service.impl;

import com.aarteaga.ms_money_exchange.entity.OfferEntity;
import com.aarteaga.ms_money_exchange.model.OfferDetail;
import com.aarteaga.ms_money_exchange.model.OfferRequestSave;
import com.aarteaga.ms_money_exchange.model.OfferResponse;
import com.aarteaga.ms_money_exchange.model.StatusCode;
import com.aarteaga.ms_money_exchange.repository.OfferRepository;
import com.aarteaga.ms_money_exchange.service.OfferService;
import com.aarteaga.ms_money_exchange.service.mapper.OfferMapper;
import com.aarteaga.ms_money_exchange.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRepository offerRepository;

    @Override
    public Flux<OfferDetail> findAll() {
        return Flux.fromIterable(offerRepository.findAll())
                .map(OfferMapper::toOfferDetail)
                .doOnSubscribe( subscription -> log.info("RequestId: {} - START"));
    }

    @Override
    public Mono<OfferResponse> create(OfferRequestSave offerRequestSave) {
        return Mono.defer(() -> {
            OfferEntity offerEntity = offerRepository.save(OfferMapper.toOfferEntityCreate(offerRequestSave));
            OfferResponse offerResponse = OfferMapper.saveOfferToOfferResponse(offerEntity);
            return Mono.just(offerResponse);
        });
    }

    @Override
    public Mono<OfferResponse> update(OfferRequestSave offerRequestSave, Integer id) {

        return Mono.defer(() -> {
            Optional<OfferEntity> offerExist = offerRepository.findById(id);

            if (offerExist.isPresent()) {
                OfferEntity offer = offerExist.get();
                OfferEntity offerEntityUpdated = offerRepository.save(OfferMapper.toOfferEntityUpdate(offer, offerRequestSave));
                OfferResponse offerResponse = OfferMapper.saveOfferToOfferResponse(offerEntityUpdated);
                return Mono.just(offerResponse);
            } else {
                return Mono.empty();
            }
        }).switchIfEmpty(Mono.just(OfferResponse.builder().status(StatusCode.builder().code("09").build()).build()))
                .onErrorResume(thr -> {
            log.error("error: {}", thr.getMessage());
            return Mono.just(OfferResponse.builder()
                    .status(StatusCode.builder().code(Constants.OPERATION_STATUS_ERROR)
                            .description("Error").build())
                    .data("Excepcion al actualizar una oferta").build());
        });
    }
}
