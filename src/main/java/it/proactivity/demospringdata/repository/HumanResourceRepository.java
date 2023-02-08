package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.HumanResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanResourceRepository extends JpaRepository<HumanResource, Long>, HumanResourceRepositoryCustom {

    // JpaRepository<CLASS, TYPE>
    // CLASS is out Entity
    // TYPE is primary key data type
}
