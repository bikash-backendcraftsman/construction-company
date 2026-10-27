package com.lbt.construction.site.web;

import com.lbt.construction.site.api.ConstructionPhase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSiteRequest(
        @NotBlank String siteCode,
        @NotBlank String name,
        @NotBlank String address,
        @NotNull ConstructionPhase phase
) {
}
