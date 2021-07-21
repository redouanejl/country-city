package com.countrycity.dto;

public class CityDTORequest {

    private String name;

    private Long country;


    public CityDTORequest() {
    }

    public CityDTORequest(String name, Long country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
