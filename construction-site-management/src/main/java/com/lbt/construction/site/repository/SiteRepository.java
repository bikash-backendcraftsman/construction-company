package com.lbt.construction.site.repository;

import com.lbt.construction.site.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {

    boolean existsBySiteCode(String siteCode);

    Optional<Site> findBySiteCode(String siteCode);
}
