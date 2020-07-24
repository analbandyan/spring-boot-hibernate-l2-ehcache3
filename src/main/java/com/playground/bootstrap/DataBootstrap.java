package com.playground.bootstrap;

import com.playground.entity.Article;
import com.playground.entity.Author;
import com.playground.entity.Book;
import com.playground.entity.Country;
import com.playground.service.AuthorService;
import com.playground.service.CountryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class DataBootstrap implements InitializingBean {

    private final AuthorService authorService;
    private final CountryService countryService;

    public DataBootstrap(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initData();
    }

    private void initData() {
        Country armenia = countryService.create(new Country().withName("Armenia"));
        Country usa = countryService.create(new Country().withName("USA"));

        Book someBook = new Book().withIsbn("some isbn").withSubject("some book subject");
        Book otherBook = new Book().withIsbn("other isbn").withSubject("other book subject");
        Book anotherBook = new Book().withIsbn("another isbn").withSubject("another book subject");

        Article someArticle = new Article().withSubject("some article subject").withBody("some article body");
        Article otherArticle = new Article().withSubject("other article subject").withBody("other article body");
        Article anotherArticle = new Article().withSubject("another article subject").withBody("another article body");

        Author poghosPoghosyan = authorService.create(
                new Author()
                        .withSsn("asdfgh")
                        .withFullName("Poghos Poghosyan")
                        .withCountryOfResidence(armenia)
                        .setBooks(Set.of(someBook, otherBook))
                        .setArticles(Set.of(someArticle, otherArticle))
        );

        authorService.create(
                new Author()
                        .withSsn("qwerty")
                        .withFullName("John Smith")
                        .withCountryOfResidence(usa)
                        .setBooks(Set.of(anotherBook))
                        .setArticles(Set.of(anotherArticle))
        );
    }

}
