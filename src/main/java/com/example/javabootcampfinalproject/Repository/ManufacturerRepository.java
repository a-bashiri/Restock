package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Integer> {
    Manufacturer findManufacturerById(Integer id);
}
