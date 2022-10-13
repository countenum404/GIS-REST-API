package com.gis.rshu.map.repository.address;

import com.gis.rshu.map.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<City, Long> {
    List<City> findAll();
    City findByCity(String city);
}
