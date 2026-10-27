package com.lbt.construction.document.repository;

import com.lbt.construction.document.domain.ProjectDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDocumentRepository extends JpaRepository<ProjectDocument, Long> {

    List<ProjectDocument> findBySiteCode(String siteCode);
}
