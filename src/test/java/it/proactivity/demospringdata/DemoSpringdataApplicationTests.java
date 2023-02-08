package it.proactivity.demospringdata;

import it.proactivity.demospringdata.model.HumanResource;
import it.proactivity.demospringdata.repository.HumanResourceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoSpringdataApplicationTests {

	@Autowired
	HumanResourceRepository humanResourceRepository;

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
	}

	@Test
	void findByNameAndUsername() {
		List<HumanResource> humanResource = humanResourceRepository.retrieveByNameAndSurname("Luigi", "Cerrato");
		if(humanResource != null && !humanResource.isEmpty())
			System.out.println(humanResource.get(0));
	}
}
