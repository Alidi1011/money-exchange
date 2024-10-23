package com.aarteaga.ms_money_exchange.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="transaction")
@Entity
public class TransactionExchange {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String username;
    private Double initialAmount;
    private Double finalAmount;
    private Double exchangeRate;
    private String originCurrency;
    private String destinyCurrency;
}
