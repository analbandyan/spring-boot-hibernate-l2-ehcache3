package com.playground.repository;

import com.playground.entity.Country;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @org.springframework.data.jpa.repository.QueryHints({
            @javax.persistence.QueryHint(name = QueryHints.CACHEABLE, value = "true")
    })
    Country getByName(String name);

    @org.springframework.data.jpa.repository.QueryHints({
            @javax.persistence.QueryHint(name = QueryHints.CACHEABLE, value = "true")
    })
    Optional<Country> findByName(String name);

    @org.springframework.data.jpa.repository.QueryHints({
            @javax.persistence.QueryHint(name = QueryHints.CACHEABLE, value = "true")
    })
    @Query("SELECT distinct c from Country c " +
            "WHERE :countryNamePart is null or c.name like %:countryNamePart%"
    )
    List<Country> getAllByNameInIgnoreNull(@Param("countryNamePart") String countryNamePart);

    @org.springframework.data.jpa.repository.QueryHints({
            @javax.persistence.QueryHint(name = QueryHints.CACHEABLE, value = "true")
    })
    @Query("SELECT distinct c from Country c " +
            "WHERE :countryNamePart is null or c.name like %:countryNamePart%"
    )
    Page<Country> getAllByNameInIgnoreNullPaged(@Param("countryNamePart") String countryNamePart, Pageable pageable);

}
