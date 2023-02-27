package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.Model.SpecialRequest;
import com.example.javabootcampfinalproject.Repository.SpecialRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialRequestsService {

    private final SpecialRequestsRepository specialRequestsRepository;

    public List<SpecialRequest> getAllSpecialRequests(){
        return specialRequestsRepository.findAll();
    }

    public void addSpecialRequest(SpecialRequest specialRequest){

    }

}
