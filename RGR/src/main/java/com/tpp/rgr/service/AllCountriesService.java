package com.tpp.rgr.service;

import com.tpp.rgr.models.AllCountries;
import com.tpp.rgr.repository.AllCountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllCountriesService {

    @Autowired
    private AllCountriesRepository allCountryRepository;

    // Get all countries
    public List<AllCountries> getAllCountries() {
        return allCountryRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    // Find country by ID
    public Optional<AllCountries> findCountryById(int id) {
        return allCountryRepository.findById(id);
    }

    // Save country
    public void saveCountry(AllCountries allCountry) {
        allCountryRepository.save(allCountry);
    }

    // Update country
    public void updateCountry(AllCountries updatedCountry) {
        AllCountries existingCountry = allCountryRepository.findById(updatedCountry.getId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));

        existingCountry.setCountryName(updatedCountry.getCountryName());
        existingCountry.setPopulation(updatedCountry.getPopulation());
        existingCountry.setAreaSqKm(updatedCountry.getAreaSqKm());
        existingCountry.setContinent(updatedCountry.getContinent());

        allCountryRepository.save(existingCountry);
    }

    // Delete country
    public void deleteCountry(int id) {
        allCountryRepository.deleteById(id);
    }
}
