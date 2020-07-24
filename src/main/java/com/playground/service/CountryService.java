package com.playground.service;

import com.playground.entity.Country;
import com.playground.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country create(Country country) {
        return countryRepository.save(country);
    }

    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    public Country getById(Long id) {
        return countryRepository.getOne(id);
    }

    public Country getByName(String name) {
        return countryRepository.getByName(name);
    }

    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }

    public List<Country> getAllByNamePart(String namePart) {
        return countryRepository.getAllByNameInIgnoreNull(namePart);
    }

    public Page<Country> getAllByNamePartPaged(String namePart, Pageable pageable) {
        return countryRepository.getAllByNameInIgnoreNullPaged(namePart, pageable);
    }
}
