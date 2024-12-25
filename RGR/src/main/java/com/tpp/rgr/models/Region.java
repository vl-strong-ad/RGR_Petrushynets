package com.tpp.rgr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionId;

    @NotBlank(message = "Назва регіону є обов'язковою")
    private String regionName;

    @NotNull(message = "Населення є обов'язковим")
    private Integer population;

    @NotBlank(message = "Країна є обов'язковою")
    private String country;

    /**
     * Геттер для ідентифікатора регіону.
     */
    public Integer getRegionId() {
        return regionId;
    }

    /**
     * Сеттер для ідентифікатора регіону.
     */
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    /**
     * Геттер для назви регіону.
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Сеттер для назви регіону.
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * Геттер для населення регіону.
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * Сеттер для населення регіону.
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

    /**
     * Геттер для країни, до якої належить регіон.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Сеттер для країни, до якої належить регіон.
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
