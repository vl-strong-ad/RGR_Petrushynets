package com.tpp.rgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tpp.rgr.models.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Integer> {
    // Вы можете добавить методы для поиска по специфическим параметрам, например:
    // Optional<Continent> findByContinentName(String continentName);
}
