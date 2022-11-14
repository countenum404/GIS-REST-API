package com.gis.rshu.map.controller;

import com.gis.rshu.map.entity.university.University;
import com.gis.rshu.map.repository.university.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("university")
public class UniversityController {
    @Autowired
    private UniversityRepository repository;
    private List<String> universityList;

    @GetMapping
    public String getUniversity(){
        universityList = repository.findAll()
                .stream()
                .map(university -> new String(university.getName()))
                .collect(Collectors.toList());
        return universityList.toString();
    }

    @DeleteMapping
    protected void deleteUniversity(@RequestBody University university){
        University toDelete = repository.findByName(university.getName());
        repository.delete(toDelete);
    }

    @PostMapping
    public void addUniversity(@RequestBody University university){
        repository.save(university);
    }
}
