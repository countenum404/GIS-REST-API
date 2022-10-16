package com.gis.rshu.map.repository.university;

import com.gis.rshu.map.entity.university.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {
    List<Building> findAll();
}
