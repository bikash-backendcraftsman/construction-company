package com.lbt.construction.document.service;

import com.lbt.construction.common.ResourceNotFoundException;
import com.lbt.construction.document.domain.ProjectDocument;
import com.lbt.construction.document.repository.ProjectDocumentRepository;
import com.lbt.construction.document.web.CreateDocumentRequest;
import com.lbt.construction.document.web.DocumentResponse;
import com.lbt.construction.site.api.SiteQueryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectDocumentService {

    private final ProjectDocumentRepository projectDocumentRepository;
    private final SiteQueryApi siteQueryApi;

    @Transactional
    public DocumentResponse create(CreateDocumentRequest request) {
        if (!siteQueryApi.existsBySiteCode(request.siteCode())) {
            throw new ResourceNotFoundException("Construction site not found: " + request.siteCode());
        }

        ProjectDocument document = new ProjectDocument();
        document.setSiteCode(request.siteCode());
        document.setTitle(request.title());
        document.setDocumentType(request.documentType());
        document.setFileName(request.fileName());
        document.setCreatedAt(Instant.now());
        return DocumentResponse.from(projectDocumentRepository.save(document));
    }

    @Transactional(readOnly = true)
    public List<DocumentResponse> list(String siteCode) {
        List<ProjectDocument> documents = siteCode == null || siteCode.isBlank()
                ? projectDocumentRepository.findAll()
                : projectDocumentRepository.findBySiteCode(siteCode);
        return documents.stream().map(DocumentResponse::from).toList();
    }
}
