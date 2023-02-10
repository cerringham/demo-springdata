package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.dto.CustomerDto;
import it.proactivity.demospringdata.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {
//elenco dei customer ordinati per nome
//elenco dei customer paginati (mostrare 6 elementi per pagina)
    @Query(value="SELECT c FROM Customer c ORDER BY c.name")
    List<Customer> findByOrderByNameAsc();

    @Query(value = "SELECT c FROM Customer c ORDER BY c.id ")
    Page<List<Customer>> findAllCustomerWithPagination(Pageable pageable);

    Customer findCustomerById(Long id);

    void deleteCustomerById(Long id);

    List<CustomerDto> findAll(List<Long> idList);

    Customer findByName(String name);
}
