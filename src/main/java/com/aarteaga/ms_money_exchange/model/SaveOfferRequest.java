package com.aarteaga.ms_money_exchange.model;

import lombok.Data;

@Data
public class SaveOfferRequest {
    private String product;
    private String cardDescription;
    private String buttonName;
    private String buttonLink;
    private String backgroundImage;
    private String additionalImage;
    private String user;
}
