package com.countrycity.mappers;

import com.countrycity.dto.CountryDTOResponse;
import com.countrycity.models.Country;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CountryResponseMapper {
    
    CountryDTOResponse countryToCountryDTOResponse(Country country);

    Country countryDTORequestToCountry(CountryDTOResponse countryDTOResponse);

    List<CountryDTOResponse> listCountriesToCountriesDTOResponse(List<Country> countries);
}
