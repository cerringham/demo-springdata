package it.proactivity.demospringdata.service;

import it.proactivity.demospringdata.model.Customer;
import it.proactivity.demospringdata.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

   public List<Customer> findAllCustomerOrderByNameDesc() {
        return customerRepository.findByOrderByNameDesc();
    }

    public List<Customer> findAllCustomerOrderByName() {
       return customerRepository.findByOrderByName();
    }

   public Page<List<Customer>> findAllCustomerWithPagination() {
        Page<List<Customer>> customers = customerRepository.findAllCustomerWithPagination(PageRequest.of(0,6));
        return customers;
    }

}
