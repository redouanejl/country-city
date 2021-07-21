package com.countrycity.models;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Field;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Country.class)
class CountryTest {

    Country country = new Country();

    @Test
    void setAndGetName() throws NoSuchFieldException {
        country.setName("Egypt");
        assertEquals("Egypt",country.getName());
    }

    @Test
    void setAndGetId() {
        country.setId(1L);

        assertEquals(1L,country.getId());
    }


    @Test
    void setAndGetCities() {
        country.setCities(new HashSet<>());

        assertEquals(new HashSet<>(),country.getCities());
    }

    @Test
    void testToString() {

        assertEquals("Country{id=null, name='null', cities=null}",country.toString());
    }
}