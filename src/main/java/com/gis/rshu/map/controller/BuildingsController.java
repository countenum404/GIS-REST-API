package com.gis.rshu.map.controller;

import com.gis.rshu.map.entity.university.Building;
import com.gis.rshu.map.entity.university.University;
import com.gis.rshu.map.repository.university.BuildingRepository;
import com.gis.rshu.map.repository.university.UniversityRepository;
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

    @Autowired
    private UniversityRepository universityRepository;

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
        if((universityRepository.findByName(building.getUniversity().getName())) == null){
            University newUniversity = new University();
            newUniversity.setName(building.getUniversity().getName());
            universityRepository.save(newUniversity);
            building.setUniversity(newUniversity);
            buildingRepository.save(building);
            return;
        }
        building.setUniversity(universityRepository.findByName(building.getUniversity().getName()));
        buildingRepository.save(building);
    }

    @DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteBuilding(@RequestBody Building building){
        Building toDelete = buildingRepository.findByName(building.getName());
        buildingRepository.deleteById(toDelete.getId());
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void changeBuilding(@RequestBody Building building){
        Building buildingToChange = buildingRepository.findByName(building.getName());
        buildingToChange = building;
        buildingRepository.save(building);
    }
}