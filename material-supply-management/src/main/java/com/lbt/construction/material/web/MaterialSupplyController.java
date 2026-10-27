package com.lbt.construction.material.web;

import com.lbt.construction.material.service.MaterialSupplyService;
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
@RequestMapping("/api/material-supplies")
@RequiredArgsConstructor
public class MaterialSupplyController {

    private final MaterialSupplyService materialSupplyService;

    @PostMapping
    public ResponseEntity<MaterialSupplyResponse> create(@Valid @RequestBody CreateMaterialSupplyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialSupplyService.create(request));
    }

    @GetMapping
    public List<MaterialSupplyResponse> list(@RequestParam(required = false) String siteCode) {
        return materialSupplyService.list(siteCode);
    }
}
