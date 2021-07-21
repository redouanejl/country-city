package com.countrycity.services.impl;

import com.countrycity.dto.CityDTORequest;
import com.countrycity.dto.CityDTOResponse;
import com.countrycity.exceptions.CityNotFoundException;
import com.countrycity.exceptions.CountryNotFoundException;
import com.countrycity.mappers.CityRequestMapper;
import com.countrycity.mappers.CityResponseMapper;
import com.countrycity.models.City;
import com.countrycity.repositories.CityRepository;
import com.countrycity.repositories.CountryRepository;
import com.countrycity.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    private CountryRepository countryRepository;

    private CityRequestMapper cityRequestMapper;

    private CityResponseMapper cityResponseMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, CityRequestMapper cityRequestMapper, CityResponseMapper cityResponseMapper) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.cityRequestMapper = cityRequestMapper;
        this.cityResponseMapper = cityResponseMapper;
    }

    @Override
    public CityDTOResponse addCity(CityDTORequest cityDTORequest) {

        countryRepository.findById(cityDTORequest.getCountry()).orElseThrow(()-> new CountryNotFoundException("Country not found!"));

        City city = cityRequestMapper.cityDTORequestToCity(cityDTORequest);

        return cityResponseMapper.cityToCityDTOResponse(cityRepository.save(city));
    }

    @Override
    public CityDTOResponse updateCity(CityDTORequest cityDTORequest, Long id) throws CountryNotFoundException {

        countryRepository.findById(cityDTORequest.getCountry()).orElseThrow(()-> new CountryNotFoundException("Country not found!"));

        City city = cityRequestMapper.cityDTORequestToCity(cityDTORequest);
        city.setId(id);
        cityRepository.save(city);

        return cityResponseMapper.cityToCityDTOResponse(city);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.findById(id).orElseThrow(()-> new CityNotFoundException("City not found!"));

        cityRepository.deleteById(id);
    }

    @Override
    public List<CityDTOResponse> getAll(Pageable pageable) {

       Page<City> cities = cityRepository.findAll(pageable);

        return cityResponseMapper.listCitiesToCitiesDTOResponse(cities.getContent());
    }

    @Override
    public CityDTOResponse getCityById(Long id) {

        City city = cityRepository.findById(id).orElseThrow(()-> new CityNotFoundException("City not found!"));

        return cityResponseMapper.cityToCityDTOResponse(city);
    }

}
