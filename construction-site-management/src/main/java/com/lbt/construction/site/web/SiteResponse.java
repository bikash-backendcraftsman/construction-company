package com.lbt.construction.site.web;

import com.lbt.construction.site.api.ConstructionPhase;
import com.lbt.construction.site.domain.Site;

import java.time.Instant;

public record SiteResponse(
        Long id,
        String siteCode,
        String name,
        String address,
        ConstructionPhase phase,
        Instant createdAt
) {
    public static SiteResponse from(Site site) {
        return new SiteResponse(
                site.getId(),
                site.getSiteCode(),
                site.getName(),
                site.getAddress(),
                site.getPhase(),
                site.getCreatedAt()
        );
    }
}
