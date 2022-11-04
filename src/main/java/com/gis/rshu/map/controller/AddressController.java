package com.gis.rshu.map.controller;

import com.gis.rshu.map.entity.address.City;
import com.gis.rshu.map.entity.address.Street;
import com.gis.rshu.map.repository.address.AddressRepository;
import com.gis.rshu.map.repository.address.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressRepository cityRepository;
    @Autowired
    private StreetRepository streetRepository;

    private List<String> cities;
    private List<String> streets;

    // address part

    @GetMapping
    public String getAddresses(){
        var addresses = streetRepository.findAll().stream()
                .map(street -> street.getCity().getCity() + " " + street.getName() + " " + street.getHouse())
                .collect(Collectors.toList());
        return addresses.toString();
    }

    // streets part

    @GetMapping("street")
    public String getStreets(){
        streets = streetRepository.findAll()
                .stream()
                .map(street -> new String(street.getName() + " " + street.getHouse()))
                .collect(Collectors.toList());
        return streets.toString();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping("street")
    public void addStreet(@RequestBody Street street){
        if ((cityRepository.findByCity(street.getCity().getCity())) == null){
            City newCity = street.getCity();
            cityRepository.save(newCity);
            street.setCity(newCity);
            streetRepository.save(street);
            return;
        }
        street.setCity((cityRepository.findByCity(street.getCity().getCity())));
        streetRepository.save(street);
    }

    //Cities part
    @GetMapping("city")
    public String getCity(){
        cities = cityRepository.findAll()
                .stream()
                .map(address -> new String(address.getCity()))
                .collect(Collectors.toList());
        return cities.toString();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping("city")
    public Boolean addCity(@RequestBody City details){
        System.out.println(details.toString());
        cityRepository.save(details);
        return cities.add(details.getCity());
    }
}
