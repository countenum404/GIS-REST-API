package com.gis.rshu.map.controller;

import com.gis.rshu.map.entity.address.City;
import com.gis.rshu.map.entity.university.Building;
import com.gis.rshu.map.entity.university.University;
import com.gis.rshu.map.repository.university.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("building")
public class BuildingsController {
    @Autowired
    private BuildingRepository buildingRepository;

    private List<String> buildings;

    @GetMapping
    public String getBuildings(){
        buildings = buildingRepository.findAll()
                .stream()
                .map(building -> new String(building.getName())).collect(Collectors.toList());
        return buildings.toString();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addBuilding(@RequestBody Building building){
        //if ()
    }
}