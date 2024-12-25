package com.tpp.rgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tpp.rgr.models.AllCountries;

@Repository
public interface AllCountriesRepository extends JpaRepository<AllCountries, Integer> {
    // Custom methods if needed, e.g.:
    // Optional<AllCountry> findByCountryName(String countryName);
}
