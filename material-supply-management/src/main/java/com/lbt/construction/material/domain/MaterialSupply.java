package com.lbt.construction.material.domain;

import com.lbt.construction.material.api.MaterialType;
import com.lbt.construction.material.api.SupplyStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "material_supplies")
public class MaterialSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "site_code", nullable = false, length = 50)
    private String siteCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "material_type", nullable = false, length = 30)
    private MaterialType materialType;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal quantity;

    @Column(nullable = false, length = 20)
    private String unit;

    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SupplyStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
