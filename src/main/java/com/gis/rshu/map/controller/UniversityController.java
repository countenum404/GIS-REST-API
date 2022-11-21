package com.gis.rshu.map.controller;

import com.gis.rshu.map.entity.university.University;
import com.gis.rshu.map.repository.university.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("university")
public class UniversityController {
    @Autowired
    private UniversityRepository repository;
    private List<University> universityList;

    @GetMapping
    public List<University> getUniversity(){
        universityList = repository.findAll();
        return this.universityList;
    }

    @GetMapping("get")
    public University getUniversityById(@RequestParam Long id){
        return repository.findById(id).get();
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
