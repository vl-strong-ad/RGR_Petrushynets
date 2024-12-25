package com.tpp.rgr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "allcountries")
public class AllCountries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "country_name", nullable = false)
    @NotBlank(message = "Назва країни є обов'язковою")
    private String countryName;

    @Column(name = "population", nullable = false)
    @NotNull(message = "Населення є обов'язковим")
    private Long population;

    @Column(name = "area_sq_km", nullable = false)
    @NotNull(message = "Площа в квадратних кілометрах є обов'язковою")
    private Long areaSqKm;

    @Column(name = "continent", nullable = false)
    @NotBlank(message = "Континент є обов'язковим")
    private String continent;

    /**
     * Геттер для ідентифікатора.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Сеттер для ідентифікатора.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Геттер для назви країни.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Сеттер для назви країни.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Геттер для населення.
     */
    public Long getPopulation() {
        return population;
    }

    /**
     * Сеттер для населення.
     */
    public void setPopulation(Long population) {
        this.population = population;
    }

    /**
     * Геттер для площі у квадратних кілометрах.
     */
    public Long getAreaSqKm() {
        return areaSqKm;
    }

    /**
     * Сеттер для площі у квадратних кілометрах.
     */
    public void setAreaSqKm(Long areaSqKm) {
        this.areaSqKm = areaSqKm;
    }

    /**
     * Геттер для континенту.
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Сеттер для континенту.
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }
}
