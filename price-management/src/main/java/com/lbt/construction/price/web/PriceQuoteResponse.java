package com.lbt.construction.price.web;

import com.lbt.construction.price.domain.PriceQuote;

import java.math.BigDecimal;
import java.time.Instant;

public record PriceQuoteResponse(
        Long id,
        String siteCode,
        String description,
        BigDecimal amount,
        String currency,
        Instant createdAt
) {
    public static PriceQuoteResponse from(PriceQuote quote) {
        return new PriceQuoteResponse(
                quote.getId(),
                quote.getSiteCode(),
                quote.getDescription(),
                quote.getAmount(),
                quote.getCurrency(),
                quote.getCreatedAt()
        );
    }
}
