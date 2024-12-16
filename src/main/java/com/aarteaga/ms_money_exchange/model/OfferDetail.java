package com.aarteaga.ms_money_exchange.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OfferDetail {

    private int id;
    private String product;
    private String cardDescription;
    private String buttonName;
    private String buttonLink;
    private String backgroundImage;
    private String additionalImage;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime modificationDate;
    private String modifiedBy;
}
