package com.playground.repository;

import com.playground.entity.Author;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @org.springframework.data.jpa.repository.QueryHints({
            @javax.persistence.QueryHint(name = QueryHints.CACHEABLE, value = "true")
    })
    Author getBySsn(String ssn);

    @org.springframework.data.jpa.repository.QueryHints({
            @javax.persistence.QueryHint(name = QueryHints.CACHEABLE, value = "true")
    })
    @Query("SELECT distinct a from Author a " +
            "INNER JOIN a.countryOfResidence c " +
            "LEFT JOIN a.books b " +
            "LEFT JOIN a.articles art " +
            "WHERE " +
            "(:name is null or a.fullName like %:name%) " +
            "AND (:bookSubject is null or b.subject like %:bookSubject%) " +
            "AND (:articleSubject is null or art.subject like %:articleSubject%) " +
            "AND (:country is null or c.name = :country) "
    )
    List<Author> query(@Param("name") String name,
                       @Param("bookSubject") String bookSubject,
                       @Param("articleSubject") String articleSubject,
                       @Param("country") String countryOfResidence);

    @org.springframework.data.jpa.repository.QueryHints({
            @javax.persistence.QueryHint(name = QueryHints.CACHEABLE, value = "true")
    })
    @Query("SELECT distinct a from Author a " +
            "INNER JOIN a.countryOfResidence c " +
            "LEFT JOIN a.books b " +
            "LEFT JOIN a.articles art " +
            "WHERE " +
            "(:name is null or a.fullName like %:name%) " +
            "AND (:bookSubject is null or b.subject like %:bookSubject%) " +
            "AND (:articleSubject is null or art.subject like %:articleSubject%) " +
            "AND (:country is null or c.name = :country) "
    )
    Page<Author> queryPaged(@Param("name") String name,
                            @Param("bookSubject") String bookSubject,
                            @Param("articleSubject") String articleSubject,
                            @Param("country") String countryOfResidence,
                            Pageable pageable);

}
