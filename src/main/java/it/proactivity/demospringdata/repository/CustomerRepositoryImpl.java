package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.Customer;
import it.proactivity.demospringdata.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Customer> findByNameEqualIgnoreCase(String name) {
        String getCustomerFromName = "SELECT c FROM Customer c " +
                "WHERE LOWER(c.name) = :name";
        Query query = entityManager.createQuery(getCustomerFromName).setParameter("name", name.toLowerCase());
        try {
            Optional<Customer> customer = Optional.of((Customer) query.getSingleResult());
            return customer;

        } catch (NoResultException e) {
            return null;
        }
    }
}
