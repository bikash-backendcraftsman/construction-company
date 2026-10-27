package com.lbt.construction.customer.web;

import com.lbt.construction.customer.domain.Customer;

import java.time.Instant;

public record CustomerResponse(
        Long id,
        String fullName,
        String phone,
        String email,
        String siteCode,
        Instant createdAt
) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getSiteCode(),
                customer.getCreatedAt()
        );
    }
}
