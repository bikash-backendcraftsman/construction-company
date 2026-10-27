package com.lbt.construction.price.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePriceQuoteRequest(
        @NotBlank String siteCode,
        @NotBlank String description,
        @NotNull @Positive BigDecimal amount,
        @Size(min = 3, max = 3) String currency
) {
}
