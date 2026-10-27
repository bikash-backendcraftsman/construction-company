package com.lbt.construction.site.web;

import com.lbt.construction.site.service.SiteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    @PostMapping
    public ResponseEntity<SiteResponse> create(@Valid @RequestBody CreateSiteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(siteService.create(request));
    }

    @GetMapping
    public List<SiteResponse> list() {
        return siteService.list();
    }

    @GetMapping("/{siteCode}")
    public SiteResponse get(@PathVariable String siteCode) {
        return siteService.getBySiteCode(siteCode);
    }
}
