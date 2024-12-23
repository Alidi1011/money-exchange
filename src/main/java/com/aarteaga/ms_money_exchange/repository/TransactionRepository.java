package com.aarteaga.ms_money_exchange.repository;

import com.aarteaga.ms_money_exchange.entity.TransactionExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionExchange, Long> {
}
