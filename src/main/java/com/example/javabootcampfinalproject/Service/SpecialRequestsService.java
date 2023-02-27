package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.Exception.ApiException;
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
        specialRequestsRepository.save(specialRequest);
    }

    public void updateSpecialRequest(Integer id, SpecialRequest newSpecialRequest){
        SpecialRequest specialRequest = specialRequestsRepository.findSpecialRequestById(id);
        if (specialRequest == null)
            throw new ApiException("ID not found",400);
        specialRequest.setRequest(newSpecialRequest.getRequest());
        specialRequest.setPrice(newSpecialRequest.getPrice());
        specialRequestsRepository.save(specialRequest);
    }

    public void deleteSpecialRequest(Integer id){
        SpecialRequest specialRequest = specialRequestsRepository.findSpecialRequestById(id);
        if (specialRequest == null)
            throw new ApiException("ID not found",400);
        specialRequestsRepository.delete(specialRequest);
    }


}
