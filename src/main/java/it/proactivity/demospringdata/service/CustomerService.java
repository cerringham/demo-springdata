package it.proactivity.demospringdata.service;

import it.proactivity.demospringdata.dto.CustomerDto;
import it.proactivity.demospringdata.model.Customer;
import it.proactivity.demospringdata.repository.CustomerRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDto getCustomerFromId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            CustomerDto customerDto = new CustomerDto(customer.get().getId(), customer.get().getName(),
                    customer.get().getEmail(), customer.get().getPhoneNumber(), customer.get().getDetail());
            return customerDto;
        } else {
            throw new NoResultException("No customer found");
        }
    }

    public List<CustomerDto> getCustomersFromIdList(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            throw new IllegalArgumentException("The list can't be null or empty");
        }
        List<Customer> customers = customerRepository.findAllById(idList);
        if (customers.isEmpty()) {
            throw new IllegalStateException("The customer list is empty");
        }
        return customers.stream()
                .map(customer -> new CustomerDto(customer.getId(), customer.getName(),
                        customer.getEmail(), customer.getPhoneNumber(), customer.getDetail()))
                .toList();
    }

    public CustomerDto getCustomerFromName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }

        Optional<Customer> customer = customerRepository.findByNameEqualIgnoreCase(name);
        if (customer.isPresent()) {
            CustomerDto customerDto = new CustomerDto(customer.get().getId(), customer.get().getName(),
                    customer.get().getEmail(), customer.get().getPhoneNumber(), customer.get().getDetail());
            return customerDto;
        } else {
            throw new NoResultException("Customer not found");
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Can't delete customer for id null");
        }
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        } else {
            throw new NoResultException("There is no customer for this id");
        }
    }

    public void deleteByIdList(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            throw new IllegalArgumentException("Id list can't be null or empty");
        }
        List<Customer> customers = customerRepository.findAllById(idList);
        if (customers.isEmpty()) {
            throw new NoResultException("The list is empty");
        }
        customerRepository.deleteAll(customers);
    }

    public void insertCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("The body can't be null");
        }
        Customer customer = createCustomer(customerDto);
        customerRepository.save(customer);
    }

    public List<CustomerDto> getAllCustomersPaginated(Integer startPage, Integer numberElementForPage) {
        PageRequest pageRequest = PageRequest.of(startPage, numberElementForPage, Sort.by("name"));

        Page<Customer> customerPage = customerRepository.findAll(pageRequest);
        return customerPage.stream()
                .map(customer -> new CustomerDto(customer.getId(), customer.getName(),
                        customer.getEmail(), customer.getPhoneNumber(), customer.getDetail()))
                .toList();
    }

    private Customer createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();

        if (customerDto.getName() == null) {
            throw new IllegalArgumentException("Name can't be null");

        } else {
            customer.setName(customerDto.getName());
        }

        if (customerDto.getEmail() == null) {
            throw new IllegalArgumentException("Email can't be null");

        } else {
            customer.setEmail(customerDto.getEmail());
        }

        if (customerDto.getPhoneNumber() == null) {
            throw new IllegalArgumentException("PhoneNumber can't be null");
        } else {
            customer.setPhoneNumber(customerDto.getPhoneNumber());
        }

        customer.setDetail(customerDto.getDetail());
        return customer;
    }
}
