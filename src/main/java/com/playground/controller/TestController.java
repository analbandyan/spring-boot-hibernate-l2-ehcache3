package com.playground.controller;

import com.playground.entity.Author;
import com.playground.entity.Country;
import com.playground.service.AuthorService;
import com.playground.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final AuthorService authorService;
    private final CountryService countryService;

    public TestController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<Object> getEnrollments(@PageableDefault Pageable pageable) {
        //run the code and investigate the logs
        Country byId1 = countryService.getById(1L);
        Country byId2 = countryService.getById(1L);

        Country byName1 = countryService.getByName("Armenia");
        Country byName2 = countryService.getByName("Armenia");

        Country fidByName1 = countryService.findByName("Armenia").orElse(null);
        Country fidByName2 = countryService.findByName("Armenia").orElse(null);

        List<Country> byNamePart1 = countryService.getAllByNamePart("men");
        List<Country> byNamePart2 = countryService.getAllByNamePart("men");

        List<Country> byNamePart3 = countryService.getAllByNamePart("Armen");
        List<Country> byNamePart4 = countryService.getAllByNamePart("Armen");

        Page<Country> byNamePartPaged1 = countryService.getAllByNamePartPaged("Arm", Pageable.unpaged());
        Page<Country> byNamePartPaged2 = countryService.getAllByNamePartPaged("Arm", Pageable.unpaged());

        Page<Country> byNamePartPaged3 = countryService.getAllByNamePartPaged("Arm", PageRequest.of(0, 2));
        Page<Country> byNamePartPaged4 = countryService.getAllByNamePartPaged("Arm", PageRequest.of(0, 2));


        Author author1 = authorService.get(1L);
        author1.getArticles().size();
        author1.getBooks().size();
        author1.getCountryOfResidence();
        Author author2 = authorService.get(1L);

        Author author3 = authorService.getBySsn("qwerty");
        Author author4 = authorService.getBySsn("qwerty");

        List<Author> queryResult1 = authorService.query("Poghos", "some", null, "Armenia");
        List<Author> queryResult2 = authorService.query("Poghos", "some", null, "Armenia");

        List<Author> queryResult3 = authorService.query(null, null, null, null);
        List<Author> queryResult4 = authorService.query(null, null, null, null);

        Page<Author> pagedQueryResult1 = authorService.queryPaged("Poghos Poghosyan", "some", null, "Armenia", Pageable.unpaged());
        Page<Author> pagedQueryResult2 = authorService.queryPaged("Poghos Poghosyan", "some", null, "Armenia", Pageable.unpaged());


        return ResponseEntity.ok("ok");
    }

}
