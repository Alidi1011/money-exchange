package com.aarteaga.ms_money_exchange.service.mapper;

import com.aarteaga.ms_money_exchange.entity.OfferEntity;
import com.aarteaga.ms_money_exchange.model.OfferDetail;
import com.aarteaga.ms_money_exchange.model.OfferRequestSave;
import com.aarteaga.ms_money_exchange.model.OfferResponse;
import com.aarteaga.ms_money_exchange.model.StatusCode;
import com.aarteaga.ms_money_exchange.util.Constants;
import com.aarteaga.ms_money_exchange.util.Utility;

import java.util.Objects;

public class OfferMapper {
    public static OfferEntity toOfferEntityCreate(OfferRequestSave saveOfferRequest) {
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

    public static OfferEntity toOfferEntityUpdate(OfferEntity offer, OfferRequestSave saveOfferRequest) {
        offer.setProduct(saveOfferRequest.getProduct());
        offer.setCardDescription(saveOfferRequest.getCardDescription());
        offer.setButtonName(saveOfferRequest.getButtonName());
        offer.setButtonLink(saveOfferRequest.getButtonLink());
        offer.setBackgroundImage(saveOfferRequest.getBackgroundImage());
        offer.setAdditionalImage(saveOfferRequest.getAdditionalImage());
        offer.setModifiedBy(saveOfferRequest.getUser());
        offer.setModificationDate(Utility.getLocalDateTime());

        return offer;
    }

    public static OfferDetail toOfferDetail(OfferEntity offerEntity) {
        return OfferDetail.builder()
                .product(offerEntity.getProduct())
                .cardDescription(offerEntity.getCardDescription())
                .buttonName(offerEntity.getButtonName())
                .buttonLink(offerEntity.getButtonLink())
                .backgroundImage(offerEntity.getBackgroundImage())
                .additionalImage(offerEntity.getAdditionalImage())
                .createdBy(offerEntity.getCreatedBy())
                .creationDate(offerEntity.getCreationDate())
                .modificationDate(offerEntity.getModificationDate())
                .modifiedBy(offerEntity.getModifiedBy())
                .build();
    }

    public static StatusCode toStatusResponse(String operationStatus, String description) {
        return StatusCode.builder()
                .code(operationStatus)
                .description(description)
                .build();
    }
    public static OfferResponse saveOfferToOfferResponse(OfferEntity offerEntity) {
        return OfferResponse.builder()
                .status(Objects.nonNull(offerEntity) ? toStatusResponse(Constants.OPERATION_STATUS_OK, Constants.OPERATION_STATUS_OK_MESSAGE)
                        : toStatusResponse(Constants.OPERATION_STATUS_ERROR, Constants.OPERATION_STATUS_ERROR_MESSAGE))
                .data("ACTION")
                .build();
    }
}
