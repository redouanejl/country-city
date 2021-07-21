package com.countrycity.services;

import com.countrycity.dto.CountryDTORequest;
import com.countrycity.dto.CountryDTOResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {

    CountryDTOResponse addCountry(CountryDTORequest country);

    CountryDTOResponse updateCountry(CountryDTORequest country,Long id);

    void deleteCountry(Long id_Country);

    List<CountryDTOResponse> getAll(Pageable pageable);

    CountryDTOResponse getCountryById(Long id);

}
