package com.aarteaga.ms_money_exchange.repository;

import com.aarteaga.ms_money_exchange.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ExchangeRepository extends JpaRepository<Exchange, Long> {
}
