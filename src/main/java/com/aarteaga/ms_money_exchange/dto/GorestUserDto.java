package com.aarteaga.ms_money_exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GorestUserDto {
    private String id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
