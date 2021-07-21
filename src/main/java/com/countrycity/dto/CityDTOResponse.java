package com.countrycity.dto;

public class CityDTOResponse {

    private Long id;

    private String name;

    private CountryDTOResponse country;

    public CityDTOResponse(Long id, String name, CountryDTOResponse country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public CityDTOResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTOResponse getCountry() {
        return country;
    }

    public void setCountry(CountryDTOResponse country) {
        this.country = country;
    }
}
