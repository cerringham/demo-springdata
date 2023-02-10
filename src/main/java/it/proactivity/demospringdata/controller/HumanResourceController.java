package it.proactivity.demospringdata.controller;

import it.proactivity.demospringdata.dto.HumanResourceDto;
import it.proactivity.demospringdata.model.HumanResource;
import it.proactivity.demospringdata.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HumanResourceController {

    @Autowired
    HumanResourceService service;

    @GetMapping(value = "/humanresource-paginated")
    public List<HumanResourceDto> getAllHumanResourcePaginated(@RequestParam Integer startPage,
                                                               @RequestParam Integer numberElementForPage) {
        return service.getAllHumanResourcePaginated(startPage, numberElementForPage);
    }
}
