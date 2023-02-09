package it.proactivity.demospringdata.repository;

import it.proactivity.demospringdata.model.HumanResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanResourceRepository extends JpaRepository<HumanResource, Long>, HumanResourceRepositoryCustom {

    List<HumanResource> findByOrderByNameAsc();

    List<HumanResource> findByNameOrderBySurnameAsc(String name);

    @Query(value="SELECT h " +
            "FROM HumanResource h " +
            "WHERE h.id >= 3")
    List<HumanResource> findHumanResourceWithBigId();

    @Query(value = "SELECT h FROM HumanResource h ORDER BY id")
    Page<List<HumanResource>> findAllHumanResourceWithPagination(Pageable pageable);

    @Query("SELECT h FROM HumanResource h WHERE h.name = ?1 and h.surname = ?2")
    HumanResource findHumanResourceByNameAndSurname(String name, String surname);

    @Query(
            value = "SELECT h.* FROM human_resource h WHERE h.name = ?1",
            nativeQuery = true)
    HumanResource findHumanResourceByNameNative(String name);

    @Query("SELECT h FROM HumanResource h WHERE h.surname = :surname and h.name = :name")
    HumanResource findUserBySurnameAndNameNamedParams(@Param("surname") String surname, @Param("name") String name);
}
