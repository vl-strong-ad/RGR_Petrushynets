package com.tpp.rgr.service;

import com.tpp.rgr.models.Continent;
import com.tpp.rgr.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    // Отримати всі континенти
    public List<Continent> getAllContinents() {
        return continentRepository.findAll(Sort.by(Sort.Order.asc("continentId")));
    }

    // Знайти континент за ID
    public Optional<Continent> findContinentById(int id) {
        return continentRepository.findById(id);
    }

    // Зберегти континент
    public void saveContinent(Continent continent) {
        continentRepository.save(continent);
    }

    // Оновити дані про континент
    public void updateContinent(Continent updatedContinent) {
        // Знайти існуючий континент за ID або кинути виняток, якщо його немає
        Continent existingContinent = continentRepository.findById(updatedContinent.getContinentId())
                .orElseThrow(() -> new IllegalArgumentException("Континент не знайдено"));

        // Оновити поля континенту
        existingContinent.setContinentName(updatedContinent.getContinentName());
        existingContinent.setPopulation(updatedContinent.getPopulation());
        existingContinent.setAreaSqKm(updatedContinent.getAreaSqKm());

        // Зберегти оновлений континент
        continentRepository.save(existingContinent);
    }

    // Видалити континент
    public void deleteContinent(int id) {
        continentRepository.deleteById(id);
    }
}
