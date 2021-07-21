package com.countrycity.dto;

public class CountryDTOResponse {

    private Long id;

    private String name;

    public CountryDTOResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CountryDTOResponse() {

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

}
