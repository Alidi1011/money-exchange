package com.aarteaga.ms_money_exchange.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusCode {
    private String code;
    private String description;
}
