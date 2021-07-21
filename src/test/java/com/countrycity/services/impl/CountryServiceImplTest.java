package com.countrycity.services.impl;

import com.countrycity.dto.CountryDTORequest;
import com.countrycity.dto.CountryDTOResponse;
import com.countrycity.mappers.CountryRequestMapper;
import com.countrycity.mappers.CountryResponseMapper;
import com.countrycity.models.Country;
import com.countrycity.repositories.CountryRepository;
import com.countrycity.services.CountryService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CountryServiceImpl.class)
class CountryServiceImplTest {
    @Autowired
    private CountryService countryService;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private CountryRequestMapper countryRequestMapper;

    @MockBean
    private CountryResponseMapper countryResponseMapper;


    @Test
    void addCountry() {

        Country country = new Country(1L,"Morocco",new HashSet<>());
        when(countryRequestMapper.countryDTORequestToCountry(any())).thenReturn(country);

        CountryDTOResponse countryDTOResponse = new CountryDTOResponse(1L,"Morocco");
        when(countryResponseMapper.countryToCountryDTOResponse(country)).thenReturn(countryDTOResponse);

        CountryDTORequest request = new CountryDTORequest("Morocco");

        CountryDTOResponse response = countryService.addCountry(request);

        assertEquals(countryDTOResponse,response);

    }

    @Test
    void updateCountry() {

        Country country = new Country(1L,"Morocco",new HashSet<>());
        when(countryRequestMapper.countryDTORequestToCountry(any())).thenReturn(country);

        CountryDTOResponse countryDTOResponse = new CountryDTOResponse(1L,"Morocco");
        when(countryResponseMapper.countryToCountryDTOResponse(country)).thenReturn(countryDTOResponse);

        CountryDTORequest request = new CountryDTORequest("Morocco");

        CountryDTOResponse response = countryService.updateCountry(request,1L);

        assertEquals(countryDTOResponse,response);
    }

    @Test
    void deleteCountry() {
        Country country = new Country(1L,"Morocco",new HashSet<>());
        when(countryRepository.findById(any())).thenReturn(Optional.of(country));
        countryService.deleteCountry(1L);
        verify(countryRepository,times(1)).deleteById(1L);
    }

    @Test
    void getAll() {

        Pageable pageable = PageRequest.of(0,9);
        List<Country> list = new ArrayList<>();
        Page<Country> countries = new PageImpl<Country>(list);

        when(countryRepository.findAll(pageable)).thenReturn(countries);

        List<CountryDTOResponse> countriesList = new ArrayList<>();

        when(countryResponseMapper.listCountriesToCountriesDTOResponse(countries.getContent())).thenReturn(countriesList);

        List<CountryDTOResponse> response = countryService.getAll(pageable);

        assertEquals(countriesList,response);

    }

    @Test
    void getCountryById() {

        Country country = new Country(1L,"Morocco",new HashSet<>());
        when(countryRepository.findById(any())).thenReturn(Optional.of(country));

        CountryDTOResponse countryDTOResponse = new CountryDTOResponse(1L,"Morocco");
        when(countryResponseMapper.countryToCountryDTOResponse(country)).thenReturn(countryDTOResponse);

        CountryDTOResponse response = countryService.getCountryById(1L);

        assertEquals(countryDTOResponse,response);

    }
}