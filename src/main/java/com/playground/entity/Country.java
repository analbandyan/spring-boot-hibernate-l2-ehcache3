package com.playground.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "country")
//@javax.persistence.Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
