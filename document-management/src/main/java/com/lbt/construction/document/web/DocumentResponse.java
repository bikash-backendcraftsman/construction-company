package com.lbt.construction.document.web;

import com.lbt.construction.document.api.DocumentType;
import com.lbt.construction.document.domain.ProjectDocument;

import java.time.Instant;

public record DocumentResponse(
        Long id,
        String siteCode,
        String title,
        DocumentType documentType,
        String fileName,
        Instant createdAt
) {
    public static DocumentResponse from(ProjectDocument document) {
        return new DocumentResponse(
                document.getId(),
                document.getSiteCode(),
                document.getTitle(),
                document.getDocumentType(),
                document.getFileName(),
                document.getCreatedAt()
        );
    }
}
