package com.countrycity.controllers;

import com.countrycity.dto.CityDTORequest;
import com.countrycity.dto.CityDTOResponse;
import com.countrycity.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<CityDTOResponse> createCity(@Valid @RequestBody CityDTORequest city){

        CityDTOResponse response =cityService.addCity(city);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTOResponse> updateCity(@Valid @RequestBody CityDTORequest city,@PathVariable Long id){

        CityDTOResponse response = cityService.updateCity(city,id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable Long id){

        cityService.deleteCity(id);

        return new ResponseEntity<String>("City with id "+id+" was deleted successfully!",HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CityDTOResponse> getAllCities(@RequestParam(defaultValue = "0", name = "page") int page,
                                                 @RequestParam(defaultValue = "9", name = "size") int size){
        Pageable pageable = PageRequest.of(page, size);
        return cityService.getAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<CityDTOResponse> getCityById(@PathVariable Long id){

        CityDTOResponse city = cityService.getCityById(id);

        return new ResponseEntity<CityDTOResponse>(city, HttpStatus.OK);
    }
}
