package com.aarteaga.ms_money_exchange.entity;


import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name="transactions")
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
    private LocalDateTime transactionDate;
}
