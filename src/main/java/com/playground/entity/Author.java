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
@Table(name = "author")
//@javax.persistence.Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "ssn", nullable = false, unique = true)
    private String ssn;

    @ToString.Include
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_book",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private Set<Book> books = new HashSet<>();

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_of_residence_id")
    private Country countryOfResidence;

    public Author setBooks(Set<Book> books) {
        this.books = books;
        books.forEach(book -> {
            Set<Author> authors = book.getAuthors();
            authors.add(this);
        });
        return this;
    }

    public Author setArticles(Set<Article> articles) {
        this.articles = articles;
        articles.forEach(article -> {
            article.setAuthor(this);
        });
        return this;
    }

}
