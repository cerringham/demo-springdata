package it.proactivity.demospringdata;

import it.proactivity.demospringdata.dto.CustomerDto;
import it.proactivity.demospringdata.model.Customer;
import it.proactivity.demospringdata.model.HumanResource;
import it.proactivity.demospringdata.repository.CustomerRepository;
import it.proactivity.demospringdata.repository.HumanResourceRepository;
import it.proactivity.demospringdata.service.CustomerService;
import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@SpringBootTest
class DemoSpringdataApplicationTests {

    @Autowired
    HumanResourceRepository humanResourceRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void findAllHumanResource() {
        List<HumanResource> list = humanResourceRepository.findAll();
        if (list.size() > 0) {
            list.stream().forEach(x -> System.out.println(x));
        } else {
            System.out.println("Something is wrong");
        }
        assertTrue(list != null);
        assertTrue(list.size() > 0);
    }

    @Test
    void findByIdList() {
        List<Long> idList = List.of(51l, 6l, 7l, 8l, 9l);
        List<HumanResource> list = humanResourceRepository.findAllById(idList);

        assertTrue(list.size() == 4);
    }

    @Test
    void findByNameAndUsername() {
        List<HumanResource> humanResource = humanResourceRepository.retrieveByNameAndSurname("Luigi", "Cerrato");
        if (humanResource != null && !humanResource.isEmpty())
            System.out.println(humanResource.get(0));
    }

    /**
     * @link https://www.baeldung.com/spring-data-sorting
     */
    @Test
    void sortingTest() {
        List<HumanResource> humanResourceList = humanResourceRepository.findByOrderByNameAsc();
        assertEquals(humanResourceList.get(0).getName(), "Alfonso");

        List<HumanResource> anotherHumanResourceList = humanResourceRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        assertEquals(humanResourceList.get(0).getName(), anotherHumanResourceList.get(0).getName());
        assertEquals(humanResourceList.get(1).getName(), anotherHumanResourceList.get(1).getName());
        assertEquals(humanResourceList.get(2).getName(), anotherHumanResourceList.get(2).getName());

        List<HumanResource> unusefulList = humanResourceRepository.findHumanResourceWithBigId();
        unusefulList.stream().forEach(x -> System.out.println(x));
        assertTrue(unusefulList.size() > 1);
    }

    // page 0, n-1
    @Test
    void paginationTest() {
        Page<HumanResource> humanResourceListPageable = humanResourceRepository.findAllHumanResourceWithPagination(PageRequest.of(
                0, 5, Sort.by("name")));

        List<HumanResource> humanResourceList = humanResourceListPageable.getContent();

        assertTrue(humanResourceList != null);
    }

    @Test
    void parameterTest() {
        HumanResource hr = null;
        hr = humanResourceRepository.findHumanResourceByNameNative("Luigi");
        assertEquals("Cerrato", hr.getSurname());

        hr = humanResourceRepository.findHumanResourceByNameAndSurname("Alfonso", "Cerrato");
        assertEquals("Cerrato", hr.getSurname());
        assertEquals("Alfonso", hr.getName());

        hr = humanResourceRepository.findUserBySurnameAndNameNamedParams("Di Capri", "Peppino");
        assertEquals("Di Capri", hr.getSurname());
        assertEquals("Peppino", hr.getName());
    }

    @Test
    void findCustomerByIdTest() {
        CustomerDto customer = customerService.getCustomerFromId(31l);
        assertTrue(customer.getName().equals("Milan A.C."));
    }

    @Test
    void findCustomerByIdNullIdNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.getCustomerFromId(null);
        });

        String message = "Id can't be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void findCustomerByIdWrongIdNegativeTest() {
        Exception exception = assertThrows(NoResultException.class, () -> {
            customerService.getCustomerFromId(80l);
        });

        String message = "No customer found";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void findCustomersFromIdListPositiveTest() {
        List<Long> idList = new ArrayList<>();
        idList.add(17l);
        idList.add(15l);
        idList.add(19l);
        idList.add(29l);

        List<CustomerDto> customerDtos = customerService.getCustomersFromIdList(idList);
        assertTrue(customerDtos.size() == 4);
    }

    @Test
    void findCustomersFromNullIdListNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.getCustomersFromIdList(null);
        });
        String message = "The list can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void findCustomersFromEmptyIdListNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Long> idList = new ArrayList<>();
            customerService.getCustomersFromIdList(idList);
        });
        String message = "The list can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void findCustomersFromWrongIdListNegativeTest() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            List<Long> idList = new ArrayList<>();
            idList.add(37l);
            idList.add(35l);
            idList.add(39l);
            idList.add(39l);
            customerService.getCustomersFromIdList(idList);
        });
        String message = "The customer list is empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void findCustomerFromNamePositiveTest() {
        CustomerDto customerDto = customerService.getCustomerFromName("PRADA");
        assertTrue(customerDto.getName().equals("prada"));
    }

    @Test
    void findCustomerFromNameNullNameNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.getCustomerFromName(null);
        });
        String message = "Name can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void findCustomerFromNameEmptyNameNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.getCustomerFromName("");
        });
        String message = "Name can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void findCustomerFromNameWrongNameNegativeTest() {
        Exception exception = assertThrows(NoResultException.class, () -> {
            customerService.getCustomerFromName("Immaginario");
        });
        String message = "Customer not found";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void deleteCustomerById() {
        Integer numberOfRecordBeforeDelete = customerRepository.findAll().size();

        customerService.deleteById(22l);

        Integer numberOfRecordAfterDelete = customerRepository.findAll().size();

        assertTrue(numberOfRecordBeforeDelete > numberOfRecordAfterDelete);
    }

    @Test
    void DeleteCustomerNullIdNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.deleteById(null);
        });
        String message = "Can't delete customer for id null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void deleteCustomerWrongIdNegativeTest() {
        Exception exception = assertThrows(NoResultException.class, () -> {
            customerService.deleteById(60l);
        });
        String message = "There is no customer for this id";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void deleteCustomerFromIdListPositiveTest() {
        List<Long> idList = new ArrayList<>();
        idList.add(5l);
        idList.add(7l);
        idList.add(11l);

        Integer numberOfRecordBeforeDelete = customerRepository.findAll().size();

        customerService.deleteByIdList(idList);

        Integer numberOfRecordAfterDelete = customerRepository.findAll().size();

        assertTrue(numberOfRecordBeforeDelete > numberOfRecordAfterDelete);
    }

    @Test
    void deleteCustomersFromIdListNullListNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.deleteByIdList(null);
        });
        String message = "Id list can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void deleteCustomersFromIdListemptyListNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Long> idList = new ArrayList<>();
            customerService.deleteByIdList(idList);
        });
        String message = "Id list can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void deleteCustomersFromIdListWrongListNegativeTest() {
        Exception exception = assertThrows(NoResultException.class, () -> {
            List<Long> idList = new ArrayList<>();
            idList.add(5l);
            idList.add(7l);
            idList.add(11l);
            customerService.deleteByIdList(idList);
        });
        String message = "The list is empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertCustomerPositiveTest() {
        Integer numberOfRecordBeforeInsert = customerRepository.findAll().size();

        CustomerDto customerDto = new CustomerDto("New Customer", "customer@customer.it",
                "1128263", "new detail");

        customerService.insertCustomer(customerDto);

        Integer numberOfRecordAfterInsert = customerRepository.findAll().size();

        assertTrue(numberOfRecordBeforeInsert < numberOfRecordAfterInsert);
    }

    @Test
    void insertCustomerNullDtoNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.insertCustomer(null);
        });
        String message = "The body can't be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertCustomerNullNameDtoNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CustomerDto customerDto = new CustomerDto(null, "customer@customer.it",
                    "1128263", "new detail");
            customerService.insertCustomer(customerDto);
        });
        String message = "Name can't be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertCustomerNullEmailDtoNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CustomerDto customerDto = new CustomerDto("name", null,
                    "1128263", "new detail");
            customerService.insertCustomer(customerDto);
        });
        String message = "Email can't be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertCustomerNullPhoneNumberDtoNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CustomerDto customerDto = new CustomerDto("name", "customer@customer.it",
                    null, "new detail");
            customerService.insertCustomer(customerDto);
        });
        String message = "PhoneNumber can't be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void paginateCustomersPositiveTest() {
        List<CustomerDto> page = customerService.getAllCustomersPaginated(0, 5);
        assertTrue(page.size() == 5);

    }
}
