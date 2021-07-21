package com.countrycity.mappers;

import com.countrycity.dto.CityDTOResponse;
import com.countrycity.models.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityResponseMapper {

    CityDTOResponse cityToCityDTOResponse(City city);

    City cityDTOResponseToCity(CityDTOResponse cityDTOResponse);

    List<CityDTOResponse> listCitiesToCitiesDTOResponse(List<City> cities);
}
