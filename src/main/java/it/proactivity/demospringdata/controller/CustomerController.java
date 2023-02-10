package it.proactivity.demospringdata.controller;

import it.proactivity.demospringdata.dto.CustomerDto;
import it.proactivity.demospringdata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomerFromId(@PathVariable Long id) {
        return customerService.getCustomerFromId(id);
    }

    @GetMapping("/customers-from-id-list")
    public List<CustomerDto> getCustomersFromIdList(@RequestBody List<Long> idList) {
        return customerService.getCustomersFromIdList(idList);
    }

    @GetMapping("/customer-from-name")
    public CustomerDto getCustomerFromName(@RequestParam String name) {
        return customerService.getCustomerFromName(name);
    }

    @RequestMapping(value = "/delete-customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteById(id);
    }

    @RequestMapping(value = "/delete-customer-from-idList", method = RequestMethod.DELETE)
    public void deleteCustomerFromIdList(@RequestBody List<Long> idList) {
        customerService.deleteByIdList(idList);
    }

    @PostMapping("/insert-customer")
    public void insertCustomer(@RequestBody CustomerDto customerDto) {
        customerService.insertCustomer(customerDto);
    }

    @GetMapping("/customers-paginated")
    public List<CustomerDto> getAllCustomersPaginated(@RequestParam Integer startPage, @RequestParam Integer numberElementForPage) {
        return customerService.getAllCustomersPaginated(startPage, numberElementForPage);
    }
}
