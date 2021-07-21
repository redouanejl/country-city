package com.countrycity.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = City.class)
class CityTest {
    City city = new City();

    @Test
    void setAndGetId() {
        city.setId(1L);

        assertEquals(1L,city.getId());
    }

    @Test
    void setAndGetName() {

        city.setName("Rome");

        assertEquals("Rome",city.getName());
    }

    @Test
    void setAndGetCountry() {
        city.setCountry(null);

        assertEquals(null,city.getCountry());
    }

    @Test
    void testToString() {
        assertEquals("City{id=null, name='null', country=null}",city.toString());
    }
}