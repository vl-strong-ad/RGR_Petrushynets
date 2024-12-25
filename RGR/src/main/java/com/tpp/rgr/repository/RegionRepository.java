package com.tpp.rgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpp.rgr.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    /**
     * Ви можете додавати власні методи для запитів.
     * Наприклад:
     * Optional<Region> findByRegionName(String regionName);
     */
}
