package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.DTO.ManufacturerDTO;
import com.example.javabootcampfinalproject.Exception.ApiException;
import com.example.javabootcampfinalproject.Model.Manufacturer;
import com.example.javabootcampfinalproject.Repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public Manufacturer getManufacturer(Integer id){
        return manufacturerRepository.findManufacturerById(id);
    }

    public List<Manufacturer> getAllManufacturers(){
        return manufacturerRepository.findAll();
    }

    public void addManufacturer(Manufacturer manufacturer){
        manufacturerRepository.save(manufacturer);
    }

    public void updateManufacturer(Integer id, ManufacturerDTO dto){
        Manufacturer oldManufacturer = manufacturerRepository.findManufacturerById(id);
        if (oldManufacturer == null)
            throw new ApiException("ID not found",400);
        oldManufacturer.setName(dto.getName());
        oldManufacturer.setCategory(dto.getCategory());
        oldManufacturer.setLocation(dto.getLocation());
        manufacturerRepository.save(oldManufacturer);
    }
}
