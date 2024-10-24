package com.aarteaga.ms_money_exchange.provider;

import com.aarteaga.ms_money_exchange.model.Exchange;

import java.util.ArrayList;
import java.util.List;

public class ExchangeProvider {

    public static List<Exchange> getExchangeList(){
        List<Exchange> customerList = new ArrayList<>();
        customerList.add(getExchange());
        return customerList;
    }

    public static Exchange getExchange() {
        return Exchange.builder()
                .id(1L)
                .originCurrency("PEN")
                .destinyCurrency("USD")
                .exchangeRate(3.74)
                .build();
    }

}
