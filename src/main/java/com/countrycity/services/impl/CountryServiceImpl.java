package com.countrycity.services.impl;

import com.countrycity.dto.CountryDTORequest;
import com.countrycity.dto.CountryDTOResponse;
import com.countrycity.exceptions.CountryNotFoundException;
import com.countrycity.mappers.CountryRequestMapper;
import com.countrycity.mappers.CountryResponseMapper;
import com.countrycity.models.Country;
import com.countrycity.repositories.CountryRepository;
import com.countrycity.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {


    private CountryRepository countryRepository;


    private CountryRequestMapper countryRequestMapper;


    private CountryResponseMapper countryResponseMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, CountryRequestMapper countryRequestMapper, CountryResponseMapper countryResponseMapper) {
        this.countryRepository = countryRepository;
        this.countryRequestMapper = countryRequestMapper;
        this.countryResponseMapper = countryResponseMapper;
    }

    @Override
    public CountryDTOResponse addCountry(CountryDTORequest countryDTORequest) {

        Country country = countryRequestMapper.countryDTORequestToCountry(countryDTORequest);
        countryRepository.save(country);
        return countryResponseMapper.countryToCountryDTOResponse(country);
    }

    @Override
    public CountryDTOResponse updateCountry(CountryDTORequest countryDTORequest, Long id) {

        Country country = countryRequestMapper.countryDTORequestToCountry(countryDTORequest);
        country.setId(id);
        countryRepository.save(country);

        return  countryResponseMapper.countryToCountryDTOResponse(country);
    }

    @Override
    public void deleteCountry(Long id_Country) {
        countryRepository.findById(id_Country).orElseThrow(()-> new CountryNotFoundException("Country not found!"));

        countryRepository.deleteById(id_Country);
    }

    @Override
    public List<CountryDTOResponse> getAll(Pageable pageable) {
        Page<Country> countries = countryRepository.findAll(pageable);

        return countryResponseMapper.listCountriesToCountriesDTOResponse(countries.getContent());
    }

    @Override
    public CountryDTOResponse getCountryById(Long id) {
        Country country = countryRepository.findById(id).orElseThrow(()-> new CountryNotFoundException("Country not found!"));

        return countryResponseMapper.countryToCountryDTOResponse(country);
    }
}
