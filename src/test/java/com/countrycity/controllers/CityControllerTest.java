package com.countrycity.controllers;

import com.countrycity.dto.CityDTORequest;
import com.countrycity.dto.CityDTOResponse;
import com.countrycity.services.CityService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CityController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class CityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private CityService cityService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void createCity() throws Exception {
        CityDTORequest request = new CityDTORequest("Rome",1L);

        CityDTOResponse response = new CityDTOResponse(1L,"Rome",null);

        when(cityService.addCity(any())).thenReturn(response);

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(toJson(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(response.getName()));
    }

    @Test
    void updateCity() throws Exception {

        CityDTORequest request = new CityDTORequest("Rome",null);

        CityDTOResponse response = new CityDTOResponse(1L,"Rome",null);

        when(cityService.updateCity(any(),any())).thenReturn(response);
        mockMvc.perform(put("/city/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(toJson(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(response.getName()));
    }

    @Test
    void deleteCity() throws Exception {
        CityDTOResponse cityDTOResponse = new CityDTOResponse(1L,"Rome",null);

        when(cityService.getCityById(1L)).thenReturn(cityDTOResponse);

        doNothing().when(cityService).deleteCity(cityDTOResponse.getId());

        mockMvc.perform(delete("/city/{id}",cityDTOResponse.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllCities() throws Exception {
        Pageable pageable = PageRequest.of(0,9);

        when(cityService.getAll(pageable)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/city/all")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

    }

    @Test
    void getCityById() throws Exception {
        CityDTOResponse response = new CityDTOResponse(1L,"Rome",null);

        when(cityService.getCityById(1L)).thenReturn(response);

        mockMvc.perform(get("/city/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(response.getName()));
    }

    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}