package com.lbt.construction.customer.service;

import com.lbt.construction.common.ResourceNotFoundException;
import com.lbt.construction.customer.domain.Customer;
import com.lbt.construction.customer.repository.CustomerRepository;
import com.lbt.construction.customer.web.CreateCustomerRequest;
import com.lbt.construction.customer.web.CustomerResponse;
import com.lbt.construction.site.api.SiteQueryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final SiteQueryApi siteQueryApi;

    @Transactional
    public CustomerResponse create(CreateCustomerRequest request) {
        if (!siteQueryApi.existsBySiteCode(request.siteCode())) {
            throw new ResourceNotFoundException("Construction site not found: " + request.siteCode());
        }

        Customer customer = new Customer();
        customer.setFullName(request.fullName());
        customer.setPhone(request.phone());
        customer.setEmail(request.email());
        customer.setSiteCode(request.siteCode());
        customer.setCreatedAt(Instant.now());
        return CustomerResponse.from(customerRepository.save(customer));
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> list(String siteCode) {
        List<Customer> customers = siteCode == null || siteCode.isBlank()
                ? customerRepository.findAll()
                : customerRepository.findBySiteCode(siteCode);
        return customers.stream().map(CustomerResponse::from).toList();
    }
}
