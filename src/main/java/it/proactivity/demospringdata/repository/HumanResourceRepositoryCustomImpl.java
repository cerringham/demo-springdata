package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.HumanResource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class HumanResourceRepositoryCustomImpl implements HumanResourceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<HumanResource> retrieveByNameAndSurname(String name, String surname) {
        String queryString = "SELECT h " +
                "FROM HumanResource h " +
                "WHERE LOWER(h.name) =: name " +
                "AND LOWER(h.surname) =: surname";
        List<HumanResource> humanResourceList = entityManager.createQuery(queryString)
                .setParameter("name", name.toLowerCase())
                .setParameter("surname", surname.toLowerCase())
                .getResultList();

        return humanResourceList;
    }
}
