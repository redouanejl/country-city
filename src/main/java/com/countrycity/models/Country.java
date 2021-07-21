package com.countrycity.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
    private Set<City> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Country() {
    }

    public Country(Long id, String name, Set<City> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }
}
