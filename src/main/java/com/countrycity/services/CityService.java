package com.countrycity.services;

import com.countrycity.dto.CityDTORequest;
import com.countrycity.dto.CityDTOResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

    CityDTOResponse addCity(CityDTORequest city);

    CityDTOResponse updateCity(CityDTORequest city,Long id);

    void deleteCity(Long id);

    List<CityDTOResponse> getAll(Pageable pageable);

    CityDTOResponse getCityById(Long id);

}
