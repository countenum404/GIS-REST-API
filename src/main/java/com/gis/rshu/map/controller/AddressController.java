package com.gis.rshu.map.controller;

import com.gis.rshu.map.entity.address.City;
import com.gis.rshu.map.entity.address.Street;
import com.gis.rshu.map.repository.address.AddressRepository;
import com.gis.rshu.map.repository.address.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AddressController {
    @Autowired
    private AddressRepository cityRepository;
    @Autowired
    private StreetRepository streetRepository;

    private List<String> cities;
    private List<String> streets;

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
    @GetMapping("address")
    public String getAddress(){
        cities = cityRepository.findAll()
                .stream()
                .map(address -> new String(address.getCity()))
                .collect(Collectors.toList());
        return cities.toString();
    }
    @GetMapping("address/{id}")
    public String getAddress(@PathVariable int id){
        return cities.get(id);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping("address")
    public Boolean addAddress(@RequestBody City details){
        System.out.println(details.toString());
        cityRepository.save(details);
        return cities.add(details.getCity());
    }
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void changeAddress(@RequestBody City details){
        City detailsToModify = cityRepository.findById(details.getId()).get();
        detailsToModify.setCity(details.getCity());
        cityRepository.save(detailsToModify);
    }
    @DeleteMapping("address/{id}")
    public void deleteAddress(@PathVariable Long id){
        cityRepository.deleteById(id);
    }
}
