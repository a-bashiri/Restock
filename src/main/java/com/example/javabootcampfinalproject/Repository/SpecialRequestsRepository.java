package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.SpecialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialRequestsRepository extends JpaRepository<SpecialRequest,Integer> {
    SpecialRequest findSpecialRequestById(Integer id);
}
