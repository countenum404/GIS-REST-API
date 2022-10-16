package com.gis.rshu.map.repository;

import com.gis.rshu.map.entity.university.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {
}
