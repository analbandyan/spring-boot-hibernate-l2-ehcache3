package com.playground.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "book")
//@javax.persistence.Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ToString.Include
    @Column(name = "subject", nullable = false)
    private String subject;

    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

}
