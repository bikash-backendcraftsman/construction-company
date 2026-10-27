package com.lbt.construction.document.web;

import com.lbt.construction.document.api.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDocumentRequest(
        @NotBlank String siteCode,
        @NotBlank String title,
        @NotNull DocumentType documentType,
        @NotBlank String fileName
) {
}
