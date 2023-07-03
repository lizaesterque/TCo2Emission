package com.example.tco2emission;

import java.math.BigDecimal;

//class created with a constructor for save the database information
public class Tco2Emission {
    int countryCode;
    String name;
    BigDecimal population;
    BigDecimal tco2;

    public Tco2Emission(Integer countryCode, String name, BigDecimal population, BigDecimal tco2) {
        this.countryCode = countryCode;
        this.name = name;
        this.population = population;
        this.tco2 = tco2;
    }
    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPopulation() {
        return population;
    }

    public void setPopulation(BigDecimal population) {
        this.population = population;
    }

    public BigDecimal getTco2() {
        return tco2;
    }

    public void settCo2 (BigDecimal tco2) {
        this.tco2 = tco2;
    }
}
