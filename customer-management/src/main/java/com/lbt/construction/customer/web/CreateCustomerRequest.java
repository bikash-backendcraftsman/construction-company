package com.lbt.construction.customer.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @NotBlank String fullName,
        @NotBlank String phone,
        @NotBlank @Email String email,
        @NotBlank String siteCode
) {
}
