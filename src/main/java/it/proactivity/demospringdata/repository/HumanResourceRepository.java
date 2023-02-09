package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.HumanResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanResourceRepository extends JpaRepository<HumanResource, Long>, HumanResourceRepositoryCustom {

    List<HumanResource> findByOrderByNameAsc();

    List<HumanResource> findByNameOrderBySurnameAsc(String name);

    @Query(value="SELECT h FROM HumanResource h WHERE h.id >= 3")
    List<HumanResource> findHumarResourceWithBigId();

    @Query(value = "SELECT u FROM HumanResource u ORDER BY id")
    Page<List<HumanResource>> findAllHumanResourceWithPagination(Pageable pageable);

}
