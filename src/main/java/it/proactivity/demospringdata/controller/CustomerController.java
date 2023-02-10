package it.proactivity.demospringdata.controller;

import it.proactivity.demospringdata.dto.CustomerDto;
import it.proactivity.demospringdata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ricerca per id singolo
//ricerca per lista di id (vi arriva in input una lista di id, anche non ordinata)
//ricerca per nome (case insensitive)
//cancellazione per id singolo
//cancellazione per lista di id (vi arriva in input una lista di id, anche non ordinata)
//inserimento nuovo Customer (solo con controllo formale sui parametri)
//lista paginata dei Customer dove si mostrano 6 Customer per pagina (sul db ci devono essere almeno 14 customer)
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/find-by-id")
    public CustomerDto getCustomerFromId(@RequestParam Long id) {
        return customerService.getCustomerFromId(id);
    }

    @PostMapping(value = "find-by-id-list")
    public List<CustomerDto> getCustomerListById(RequestBody List<Long> idList) {
        return customerService.getCustomerListById(idList);
    }

    @GetMapping(value = "find-by-name")
    public CustomerDto getCustomerFromName(@RequestParam String name) {
        return customerService.getCustomerByName(name);
    }

    @DeleteMapping(value = "/delete-by-id")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
