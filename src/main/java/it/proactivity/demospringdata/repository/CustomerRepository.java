package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {
    Optional<Customer> findByNameEqualIgnoreCase(String name);
}
