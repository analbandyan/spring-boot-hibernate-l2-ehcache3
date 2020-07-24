package com.playground.service;

import com.playground.entity.Author;
import com.playground.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public Author get(Long id) {
        return authorRepository.getOne(id);
    }

    public Author getBySsn(String ssn) {
        return authorRepository.getBySsn(ssn);
    }

    public List<Author> query(String name, String bookSubject, String articleSubject, String countryOfResidence) {
        return authorRepository.query(name, bookSubject, articleSubject, countryOfResidence);
    }

    public Page<Author> queryPaged(String name, String bookSubject, String articleSubject, String countryOfResidence, Pageable pageable) {
        return authorRepository.queryPaged(name, bookSubject, articleSubject, countryOfResidence, pageable);
    }
}
