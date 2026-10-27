package com.lbt.construction.site.service;

import com.lbt.construction.common.ConflictException;
import com.lbt.construction.common.ResourceNotFoundException;
import com.lbt.construction.site.domain.Site;
import com.lbt.construction.site.repository.SiteRepository;
import com.lbt.construction.site.web.CreateSiteRequest;
import com.lbt.construction.site.web.SiteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;

    @Transactional
    public SiteResponse create(CreateSiteRequest request) {
        if (siteRepository.existsBySiteCode(request.siteCode())) {
            throw new ConflictException("Site code already exists: " + request.siteCode());
        }

        Site site = new Site();
        site.setSiteCode(request.siteCode());
        site.setName(request.name());
        site.setAddress(request.address());
        site.setPhase(request.phase());
        site.setCreatedAt(Instant.now());
        return SiteResponse.from(siteRepository.save(site));
    }

    @Transactional(readOnly = true)
    public List<SiteResponse> list() {
        return siteRepository.findAll().stream().map(SiteResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public SiteResponse getBySiteCode(String siteCode) {
        return siteRepository.findBySiteCode(siteCode)
                .map(SiteResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Construction site not found: " + siteCode));
    }
}
