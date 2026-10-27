package com.lbt.construction.material.service;

import com.lbt.construction.common.ResourceNotFoundException;
import com.lbt.construction.material.api.SupplyStatus;
import com.lbt.construction.material.domain.MaterialSupply;
import com.lbt.construction.material.repository.MaterialSupplyRepository;
import com.lbt.construction.material.web.CreateMaterialSupplyRequest;
import com.lbt.construction.material.web.MaterialSupplyResponse;
import com.lbt.construction.site.api.SiteQueryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialSupplyService {

    private final MaterialSupplyRepository materialSupplyRepository;
    private final SiteQueryApi siteQueryApi;

    @Transactional
    public MaterialSupplyResponse create(CreateMaterialSupplyRequest request) {
        requireSite(request.siteCode());

        MaterialSupply supply = new MaterialSupply();
        supply.setSiteCode(request.siteCode());
        supply.setMaterialType(request.materialType());
        supply.setQuantity(request.quantity());
        supply.setUnit(request.unit());
        supply.setSupplierName(request.supplierName());
        supply.setStatus(SupplyStatus.REQUESTED);
        supply.setCreatedAt(Instant.now());
        return MaterialSupplyResponse.from(materialSupplyRepository.save(supply));
    }

    @Transactional(readOnly = true)
    public List<MaterialSupplyResponse> list(String siteCode) {
        List<MaterialSupply> supplies = siteCode == null || siteCode.isBlank()
                ? materialSupplyRepository.findAll()
                : materialSupplyRepository.findBySiteCode(siteCode);
        return supplies.stream().map(MaterialSupplyResponse::from).toList();
    }

    private void requireSite(String siteCode) {
        if (!siteQueryApi.existsBySiteCode(siteCode)) {
            throw new ResourceNotFoundException("Construction site not found: " + siteCode);
        }
    }
}
