package com.aarteaga.ms_money_exchange.service.mapper;

import com.aarteaga.ms_money_exchange.entity.OfferEntity;
import com.aarteaga.ms_money_exchange.model.SaveOfferRequest;
import com.aarteaga.ms_money_exchange.util.Constants;
import com.aarteaga.ms_money_exchange.util.Utility;

public class OfferMapper {
    public static OfferEntity toOfferEntityCreate(SaveOfferRequest saveOfferRequest) {
        return OfferEntity.builder()
                .product(saveOfferRequest.getProduct())
                .cardDescription(saveOfferRequest.getCardDescription())
                .buttonName(saveOfferRequest.getButtonName())
                .buttonLink(saveOfferRequest.getButtonLink())
                .backgroundImage(saveOfferRequest.getBackgroundImage())
                .additionalImage(saveOfferRequest.getAdditionalImage())
                .createdBy(saveOfferRequest.getUser())
                .creationDate(Utility.getLocalDateTime())
                .build();
    }

    public static OfferEntity toOfferEntityUpdate(OfferEntity offer, SaveOfferRequest saveOfferRequest) {
        offer.setProduct(saveOfferRequest.getProduct());
        offer.setCardDescription(saveOfferRequest.getProduct());
        offer.setButtonName(saveOfferRequest.getButtonName());
        offer.setButtonLink(saveOfferRequest.getButtonLink());
        offer.setBackgroundImage(saveOfferRequest.getBackgroundImage());
        offer.setAdditionalImage(saveOfferRequest.getAdditionalImage());
        offer.setModifiedBy(saveOfferRequest.getUser());
        offer.setModificationDate(Utility.getLocalDateTime());

        return offer;
    }
}
