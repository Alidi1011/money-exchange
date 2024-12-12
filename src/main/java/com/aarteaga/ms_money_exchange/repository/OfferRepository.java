package com.aarteaga.ms_money_exchange.repository;

import com.aarteaga.ms_money_exchange.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferEntity, Integer> {
}
