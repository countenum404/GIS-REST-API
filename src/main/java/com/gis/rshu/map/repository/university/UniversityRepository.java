package com.gis.rshu.map.repository.university;

import com.gis.rshu.map.entity.university.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {
    List<University> findAll();
    University findByName(String name);
}
