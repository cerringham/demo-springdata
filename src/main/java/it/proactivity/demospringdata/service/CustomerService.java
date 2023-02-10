package it.proactivity.demospringdata.service;

import it.proactivity.demospringdata.dto.CustomerDto;
import it.proactivity.demospringdata.model.Customer;
import it.proactivity.demospringdata.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerDto getCustomerFromId(Long id) {
        Customer  c = customerRepository.findCustomerById(id);
        return new CustomerDto(c.getId(), c.getName(), c.getEmail(), c.getPhoneNumber(), c.getDetail());
    }
    public List<CustomerDto> getCustomerListById(List<Long> idList) {
        return customerRepository.findAll(idList);
    }

    public CustomerDto getCustomerByName(String name) {
        Customer c = customerRepository.findByName(name);
        return new CustomerDto(c.getId(), c.getName(), c.getEmail(), c.getPhoneNumber(), c.getDetail());
    }
    public void deleteCustomerById(Long id) {
        customerRepository.deleteCustomerById(id);
    }


}
