package com.lbt.construction.material.web;

import com.lbt.construction.material.api.MaterialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateMaterialSupplyRequest(
        @NotBlank String siteCode,
        @NotNull MaterialType materialType,
        @NotNull @Positive BigDecimal quantity,
        @NotBlank String unit,
        @NotBlank String supplierName
) {
}
