package com.countrycity.services.impl;

import com.countrycity.dto.CityDTORequest;
import com.countrycity.dto.CityDTOResponse;
import com.countrycity.mappers.CityRequestMapper;
import com.countrycity.mappers.CityResponseMapper;
import com.countrycity.models.City;
import com.countrycity.models.Country;
import com.countrycity.repositories.CityRepository;
import com.countrycity.repositories.CountryRepository;
import com.countrycity.services.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CityServiceImpl.class)
class CityServiceImplTest {

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private CityRequestMapper cityRequestMapper;

    @MockBean
    private CityResponseMapper cityResponseMapper;


    @Test
    void addCity() {
        Country country = new Country(1L,"Morocco",new HashSet<>());
        when(countryRepository.findById(any())).thenReturn(Optional.of(country));

        City city = new City(1L,"Marrakech",country);
        when(cityRequestMapper.cityDTORequestToCity(any())).thenReturn(city);

        CityDTOResponse cityDTOResponse = new CityDTOResponse(1L,"Marrakech",null);
        when(cityResponseMapper.cityToCityDTOResponse(city)).thenReturn(cityDTOResponse);

        CityDTORequest cityDTORequest = new CityDTORequest("Marrakech",1L);

        CityDTOResponse response = cityService.addCity(cityDTORequest);

        assertEquals(cityDTOResponse.getName(),response.getName());
    }

    @Test
    void updateCity() {
        Country country = new Country(1L,"Morocco",new HashSet<>());
        when(countryRepository.findById(any())).thenReturn(Optional.of(country));

        City city = new City(1L,"Marrakech",country);
        when(cityRequestMapper.cityDTORequestToCity(any())).thenReturn(city);

        CityDTOResponse cityDTOResponse = new CityDTOResponse(1L,"Marrakech",null);
        when(cityResponseMapper.cityToCityDTOResponse(city)).thenReturn(cityDTOResponse);

        CityDTORequest cityDTORequest = new CityDTORequest("Marrakech",1L);

        CityDTOResponse response = cityService.updateCity(cityDTORequest,1L);

        assertEquals(cityDTOResponse.getName(),response.getName());
    }

    @Test
    void deleteCity() {

        Country country = new Country(1L,"Morocco",new HashSet<>());
        City city = new City(1L,"Tangier",country);
        when(cityRepository.findById(any())).thenReturn(Optional.of(city));

        cityService.deleteCity(1L);
        verify(cityRepository,times(1)).deleteById(1L);
    }

    @Test
    void getAll() {

        Pageable pageable = PageRequest.of(0, 9);
        List<City> list = new ArrayList<>();
        Page<City> cities = new PageImpl<City>(list);

        when(cityRepository.findAll(pageable)).thenReturn(cities);

        List<CityDTOResponse> citiesList = new ArrayList<>();

        when(cityResponseMapper.listCitiesToCitiesDTOResponse(cities.getContent())).thenReturn(citiesList);

        List<CityDTOResponse> response = cityService.getAll(pageable);

        assertEquals(citiesList,response);
    }

    @Test
    void getCityById() {

        Country country = new Country(1L,"Morocco",new HashSet<>());
        City city = new City(1L,"Casablanca",country);
        when(cityRepository.findById(any())).thenReturn(Optional.of(city));

        CityDTOResponse cityDTOResponse = new CityDTOResponse(1L,"Casablanca",null);
        when(cityResponseMapper.cityToCityDTOResponse(city)).thenReturn(cityDTOResponse);

        CityDTOResponse response = cityService.getCityById(1L);

        assertEquals(cityDTOResponse, response);
    }
}