package com.lbt.construction.price.service;

import com.lbt.construction.common.ResourceNotFoundException;
import com.lbt.construction.price.domain.PriceQuote;
import com.lbt.construction.price.repository.PriceQuoteRepository;
import com.lbt.construction.price.web.CreatePriceQuoteRequest;
import com.lbt.construction.price.web.PriceQuoteResponse;
import com.lbt.construction.site.api.SiteQueryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceQuoteService {

    private final PriceQuoteRepository priceQuoteRepository;
    private final SiteQueryApi siteQueryApi;

    @Transactional
    public PriceQuoteResponse create(CreatePriceQuoteRequest request) {
        if (!siteQueryApi.existsBySiteCode(request.siteCode())) {
            throw new ResourceNotFoundException("Construction site not found: " + request.siteCode());
        }

        PriceQuote quote = new PriceQuote();
        quote.setSiteCode(request.siteCode());
        quote.setDescription(request.description());
        quote.setAmount(request.amount());
        quote.setCurrency(request.currency() == null || request.currency().isBlank() ? "INR" : request.currency());
        quote.setCreatedAt(Instant.now());
        return PriceQuoteResponse.from(priceQuoteRepository.save(quote));
    }

    @Transactional(readOnly = true)
    public List<PriceQuoteResponse> list(String siteCode) {
        List<PriceQuote> quotes = siteCode == null || siteCode.isBlank()
                ? priceQuoteRepository.findAll()
                : priceQuoteRepository.findBySiteCode(siteCode);
        return quotes.stream().map(PriceQuoteResponse::from).toList();
    }
}
