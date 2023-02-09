package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.HumanResource;

import java.util.List;

public interface HumanResourceRepositoryCustom {

    List<HumanResource> retrieveByNameAndSurname(String name, String surname);

}
