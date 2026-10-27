package com.lbt.construction.material.web;

import com.lbt.construction.material.api.MaterialType;
import com.lbt.construction.material.api.SupplyStatus;
import com.lbt.construction.material.domain.MaterialSupply;

import java.math.BigDecimal;
import java.time.Instant;

public record MaterialSupplyResponse(
        Long id,
        String siteCode,
        MaterialType materialType,
        BigDecimal quantity,
        String unit,
        String supplierName,
        SupplyStatus status,
        Instant createdAt
) {
    public static MaterialSupplyResponse from(MaterialSupply supply) {
        return new MaterialSupplyResponse(
                supply.getId(),
                supply.getSiteCode(),
                supply.getMaterialType(),
                supply.getQuantity(),
                supply.getUnit(),
                supply.getSupplierName(),
                supply.getStatus(),
                supply.getCreatedAt()
        );
    }
}
