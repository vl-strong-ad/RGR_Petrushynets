package com.tpp.rgr.service;

import com.tpp.rgr.models.Region;
import com.tpp.rgr.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    // Отримати всі регіони
    public List<Region> getAllRegions() {
        return regionRepository.findAll(Sort.by(Sort.Order.asc("regionId")));
    }

    // Знайти регіон за ID
    public Optional<Region> findRegionById(int id) {
        return regionRepository.findById(id);
    }

    // Зберегти регіон
    public void saveRegion(Region region) {
        regionRepository.save(region);
    }

    // Виняток для обробки залежностей регіону
    public static class RegionHasDependenciesException extends RuntimeException {
        public RegionHasDependenciesException(String message) {
            super(message);
        }
    }

    // Оновити дані про регіон
    public void updateRegion(Region updatedRegion) {
        // Знайти існуючий регіон за ID, або кинути виняток, якщо його немає
        Region existingRegion = regionRepository.findById(updatedRegion.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Регіон не знайдено"));

        // Оновлення полів регіону
        existingRegion.setRegionName(updatedRegion.getRegionName());
        existingRegion.setPopulation(updatedRegion.getPopulation());
        existingRegion.setCountry(updatedRegion.getCountry());

        // Зберегти оновлений регіон
        regionRepository.save(existingRegion);
    }

    // Видалити регіон
    public void deleteRegion(int id) {
        // Якщо потрібно обробити залежності, можна додати логіку тут
        regionRepository.deleteById(id);
    }
}
