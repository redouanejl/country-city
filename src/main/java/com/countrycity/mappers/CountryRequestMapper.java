package com.countrycity.mappers;

import com.countrycity.dto.CountryDTORequest;
import com.countrycity.models.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryRequestMapper {

    CountryDTORequest countryToCountryDTORequest(Country country);

    Country countryDTORequestToCountry(CountryDTORequest countryDTORequest);

}
