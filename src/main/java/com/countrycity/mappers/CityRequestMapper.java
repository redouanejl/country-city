package com.countrycity.mappers;

import com.countrycity.dto.CityDTORequest;
import com.countrycity.models.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityRequestMapper {

    @Mapping(source = "country.id", target = "country")
    CityDTORequest cityToCityDTORequest(City city);

    @Mapping(source = "country", target = "country.id")
    City cityDTORequestToCity(CityDTORequest cityDTORequest);

}
