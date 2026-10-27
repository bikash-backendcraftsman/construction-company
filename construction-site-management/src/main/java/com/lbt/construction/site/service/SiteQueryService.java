package com.lbt.construction.site.service;

import com.lbt.construction.site.api.SiteQueryApi;
import com.lbt.construction.site.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SiteQueryService implements SiteQueryApi {

    private final SiteRepository siteRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySiteCode(String siteCode) {
        return siteRepository.existsBySiteCode(siteCode);
    }
}
