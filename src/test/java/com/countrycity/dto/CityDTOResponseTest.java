package com.countrycity.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CityDTOResponse.class)
class CityDTOResponseTest {

    CityDTOResponse cityDTOResponse = new CityDTOResponse();

    @Test
    void setId() {
        cityDTOResponse.setId(1L);

        assertEquals(1L,cityDTOResponse.getId());
    }

    @Test
    void setName() {
        cityDTOResponse.setName("Rome");

        assertEquals("Rome",cityDTOResponse.getName());
    }

    @Test
    void setCountry() {
        cityDTOResponse.setCountry(null);

        assertEquals(null,cityDTOResponse.getCountry());
    }
}