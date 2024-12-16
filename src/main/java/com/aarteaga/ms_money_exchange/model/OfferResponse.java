package com.aarteaga.ms_money_exchange.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfferResponse {
    private StatusCode status;
    private String data;
}
