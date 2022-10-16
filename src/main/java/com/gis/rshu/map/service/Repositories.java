package com.gis.rshu.map.service;

import com.gis.rshu.map.repository.address.AddressRepository;
import com.gis.rshu.map.repository.address.StreetRepository;
import com.gis.rshu.map.repository.university.BuildingRepository;
import com.gis.rshu.map.repository.university.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Repositories {

    @Autowired
    public BuildingRepository buildingRepository;

    @Autowired
    public UniversityRepository universityRepository;

    @Autowired
    public AddressRepository addressRepository;

    @Autowired
    public StreetRepository streetRepository;

}
