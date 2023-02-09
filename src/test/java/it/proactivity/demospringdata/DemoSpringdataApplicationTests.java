package it.proactivity.demospringdata;

import it.proactivity.demospringdata.model.Customer;
import it.proactivity.demospringdata.model.HumanResource;
import it.proactivity.demospringdata.repository.HumanResourceRepository;
import it.proactivity.demospringdata.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class DemoSpringdataApplicationTests {

	@Autowired
	HumanResourceRepository humanResourceRepository;

	@Autowired
	CustomerService customerService;

	@Test
	void contextLoads() {
	}

	@Test
	void findAllHumanResource() {
		List<HumanResource> list = humanResourceRepository.findAll();
		if(list.size() > 0) {
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
		if(humanResource != null && !humanResource.isEmpty())
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
		Page<List<HumanResource>> humanResourceList = humanResourceRepository.findAllHumanResourceWithPagination(PageRequest.of(
				0, 5, Sort.by("name")));
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
	void findAllCustomerOrderByName() {
		List<Customer> customers = customerService.findAllCustomerOrderByNameDesc();
		customers.stream()
				.forEach(System.out::println);
	}

	@Test
	void findAllCustomerWithPagination() {
		Page<List<Customer>> customers = customerService.findAllCustomerWithPagination();
		assertTrue(customers.getSize() == 6);
	}

}
