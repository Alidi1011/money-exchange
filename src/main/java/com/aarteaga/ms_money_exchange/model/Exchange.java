package com.aarteaga.ms_money_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="exchange")
@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String originCurrency;
    private String destinyCurrency;
    private Double exchangeRate;

}
