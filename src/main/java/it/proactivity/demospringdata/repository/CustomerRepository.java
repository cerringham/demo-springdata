package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByOrderByNameDesc();

    List<Customer> findByOrderByName();

    @Query(value = "SELECT c FROM Customer c")
    Page<List<Customer>> findAllCustomerWithPagination(Pageable pageable);

}
