package com.lbt.construction.customer.repository;

import com.lbt.construction.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findBySiteCode(String siteCode);
}
