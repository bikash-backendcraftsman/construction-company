package com.lbt.construction.material.repository;

import com.lbt.construction.material.domain.MaterialSupply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialSupplyRepository extends JpaRepository<MaterialSupply, Long> {

    List<MaterialSupply> findBySiteCode(String siteCode);
}
