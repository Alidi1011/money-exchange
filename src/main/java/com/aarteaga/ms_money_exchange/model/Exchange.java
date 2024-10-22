package com.aarteaga.ms_money_exchange.model;

import jakarta.persistence.*;
import lombok.Data;

//import javax.persistence.*;

@Data
@Table(name="exchange")
@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    private String originCurrency;
    private String destinyCurrency;
    private Double exchangeRate;
}
