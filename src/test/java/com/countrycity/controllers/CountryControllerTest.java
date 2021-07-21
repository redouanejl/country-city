package com.countrycity.controllers;

import com.countrycity.dto.CountryDTORequest;
import com.countrycity.dto.CountryDTOResponse;
import com.countrycity.services.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CountryController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class CountryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void createCountry() throws Exception {
        CountryDTORequest request = new CountryDTORequest("Morocco");

        CountryDTOResponse countryDTOResponse = new CountryDTOResponse(1L, "Morocco");

        when(countryService.addCountry(any())).thenReturn(countryDTOResponse);


        mockMvc.perform(post("/country")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(toJson(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(countryDTOResponse.getId()))
                .andExpect(jsonPath("$.name").value(countryDTOResponse.getName()));
    }

    @Test
    void updateCountry() throws Exception {

        CountryDTORequest request = new CountryDTORequest("Morocco");

        CountryDTOResponse countryDTOResponse = new CountryDTOResponse(1L, "Morocco");

        when(countryService.updateCountry(any(), any())).thenReturn(countryDTOResponse);
        mockMvc.perform(put("/country/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(toJson(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(countryDTOResponse.getId()))
                .andExpect(jsonPath("$.name").value(countryDTOResponse.getName()));

    }

    @Test
    void deleteCountry() throws Exception {
        CountryDTOResponse countryDTOResponse = new CountryDTOResponse(1L, "Morocco");

        when(countryService.getCountryById(any())).thenReturn(countryDTOResponse);

        doNothing().when(countryService).deleteCountry(countryDTOResponse.getId());

        mockMvc.perform(delete("/country/{id}", countryDTOResponse.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void getAllCountries() throws Exception {
        Pageable pageable = PageRequest.of(0, 9);

        when(countryService.getAll(pageable)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/country/all")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

    }

    @Test
    void getCountryById() throws Exception {
        CountryDTOResponse countryDTOResponse = new CountryDTOResponse(1L, "Morocco");

        when(countryService.getCountryById(any())).thenReturn(countryDTOResponse);

        mockMvc.perform(get("/country/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(countryDTOResponse.getId()))
                .andExpect(jsonPath("$.name").value(countryDTOResponse.getName()));
    }

    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}