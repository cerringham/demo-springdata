package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.Customer;

import java.util.Optional;

public interface CustomerRepositoryCustom {
    Optional<Customer> findByNameEqualIgnoreCase(String name);
}
