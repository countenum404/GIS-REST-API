package com.gis.rshu.map.repository.address;

import com.gis.rshu.map.entity.address.Street;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends CrudRepository<Street, Long> {
    List<Street> findAll();
}
