package com.lbt.construction.document.web;

import com.lbt.construction.document.service.ProjectDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class ProjectDocumentController {

    private final ProjectDocumentService projectDocumentService;

    @PostMapping
    public ResponseEntity<DocumentResponse> create(@Valid @RequestBody CreateDocumentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDocumentService.create(request));
    }

    @GetMapping
    public List<DocumentResponse> list(@RequestParam(required = false) String siteCode) {
        return projectDocumentService.list(siteCode);
    }
}
