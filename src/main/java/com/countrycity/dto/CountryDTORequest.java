package com.countrycity.dto;


import javax.validation.constraints.NotBlank;

public class CountryDTORequest {

    @NotBlank(message = "Country name is required!")
    private String name;

    public CountryDTORequest() {
    }

    public CountryDTORequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
