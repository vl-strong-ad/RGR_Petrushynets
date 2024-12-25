package com.tpp.rgr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "continent")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continent_id")
    private Integer continentId;

    @NotBlank(message = "Назва континенту є обов'язковою")
    @Column(name = "continent_name")
    private String continentName;

    @NotNull(message = "Населення є обов'язковим")
    private Long population;

    @NotNull(message = "Площа в квадратних кілометрах є обов'язковою")
    @Column(name = "area_sq_km")
    private Long areaSqKm;

    /**
     * Геттер для ідентифікатора континенту.
     */
    public Integer getContinentId() {
        return continentId;
    }

    /**
     * Сеттер для ідентифікатора континенту.
     */
    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    /**
     * Геттер для назви континенту.
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * Сеттер для назви континенту.
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    /**
     * Геттер для населення континенту.
     */
    public Long getPopulation() {
        return population;
    }

    /**
     * Сеттер для населення континенту.
     */
    public void setPopulation(Long population) {
        this.population = population;
    }

    /**
     * Геттер для площі континенту у квадратних кілометрах.
     */
    public Long getAreaSqKm() {
        return areaSqKm;
    }

    /**
     * Сеттер для площі континенту у квадратних кілометрах.
     */
    public void setAreaSqKm(Long areaSqKm) {
        this.areaSqKm = areaSqKm;
    }
}
