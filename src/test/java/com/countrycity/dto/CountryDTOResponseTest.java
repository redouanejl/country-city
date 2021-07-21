package com.countrycity.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CountryDTOResponse.class)
class CountryDTOResponseTest {

    CountryDTOResponse countryDTOResponse = new CountryDTOResponse();
    @Test
    void setId() {
        countryDTOResponse.setId(1L);

        assertEquals(1L,countryDTOResponse.getId());
    }

    @Test
    void setName() {
        countryDTOResponse.setName("Germany");
        assertEquals("Germany",countryDTOResponse.getName());
    }
}