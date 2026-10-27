package com.lbt.construction.price.repository;

import com.lbt.construction.price.domain.PriceQuote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceQuoteRepository extends JpaRepository<PriceQuote, Long> {

    List<PriceQuote> findBySiteCode(String siteCode);
}
