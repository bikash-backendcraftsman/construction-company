package com.lbt.construction.price.web;

import com.lbt.construction.price.service.PriceQuoteService;
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
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceQuoteController {

    private final PriceQuoteService priceQuoteService;

    @PostMapping
    public ResponseEntity<PriceQuoteResponse> create(@Valid @RequestBody CreatePriceQuoteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(priceQuoteService.create(request));
    }

    @GetMapping
    public List<PriceQuoteResponse> list(@RequestParam(required = false) String siteCode) {
        return priceQuoteService.list(siteCode);
    }
}
