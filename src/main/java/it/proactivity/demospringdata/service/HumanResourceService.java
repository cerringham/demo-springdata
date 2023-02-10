package it.proactivity.demospringdata.service;

import it.proactivity.demospringdata.dto.HumanResourceDto;
import it.proactivity.demospringdata.repository.HumanResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HumanResourceService {

    @Autowired
    HumanResourceRepository repository;

    public List<HumanResourceDto> getAllHumanResourcePaginated(Integer startPage, Integer numberElementForPage) {
        PageRequest pageRequest = PageRequest.of(startPage, numberElementForPage, Sort.by("name"));

        return repository.findAllHumanResourceWithPagination(pageRequest)
                .stream()
                .map(x -> new HumanResourceDto(x.getId(), x.getName(), x.getSurname(), x.getEmail(),
                        x.getPhoneNumber(), x.getVatCode(), x.getIsCeo(), x.getIsCda())).collect(Collectors.toList());
    }
}
