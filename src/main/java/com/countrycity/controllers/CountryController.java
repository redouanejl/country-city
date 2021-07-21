package com.countrycity.controllers;

import com.countrycity.dto.CountryDTORequest;
import com.countrycity.dto.CountryDTOResponse;
import com.countrycity.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<CountryDTOResponse> createCountry(@Valid @RequestBody CountryDTORequest country){

        CountryDTOResponse response =countryService.addCountry(country);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTOResponse> updateCountry(@Valid @RequestBody CountryDTORequest country,@PathVariable Long id){

        CountryDTOResponse response = countryService.updateCountry(country,id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable Long id){

        countryService.deleteCountry(id);

        return new ResponseEntity<String>("Country with id "+id+" was deleted successfully!",HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CountryDTOResponse> getAllCountries(@RequestParam(defaultValue = "0", name = "page") int page,
                                                    @RequestParam(defaultValue = "9", name = "size") int size){

        Pageable pageable = PageRequest.of(page, size);

        return countryService.getAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<CountryDTOResponse> getCountryById(@PathVariable Long id){

        CountryDTOResponse country = countryService.getCountryById(id);

        return new ResponseEntity<CountryDTOResponse>(country, HttpStatus.OK);
    }

}
