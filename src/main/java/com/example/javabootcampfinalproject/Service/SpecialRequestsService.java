package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.Exception.ApiException;
import com.example.javabootcampfinalproject.Model.SpecialRequest;
import com.example.javabootcampfinalproject.Repository.SpecialRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialRequestsService {

    private final SpecialRequestsRepository specialRequestsRepository;


    public SpecialRequest getSpecialRequest(Integer id){
        return specialRequestsRepository.findSpecialRequestById(id);
    }

    public List<SpecialRequest> getAllSpecialRequests(){
        return specialRequestsRepository.findAll();
    }

    //TODO: Admin only
    public void addSpecialRequest(SpecialRequest specialRequest){
        specialRequestsRepository.save(specialRequest);
    }

    //TODO: Admin only
    public void updateSpecialRequest(Integer id, SpecialRequest newSpecialRequest){
        SpecialRequest specialRequest = specialRequestsRepository.findSpecialRequestById(id);
        if (specialRequest == null)
            throw new ApiException("ID not found",404);
        specialRequest.setRequest(newSpecialRequest.getRequest());
        specialRequest.setPrice(newSpecialRequest.getPrice());
        specialRequestsRepository.save(specialRequest);
    }

    //TODO: Admin only
    public void deleteSpecialRequest(Integer id){
        SpecialRequest specialRequest = specialRequestsRepository.findSpecialRequestById(id);
        if (specialRequest == null)
            throw new ApiException("ID not found",404);
        specialRequestsRepository.delete(specialRequest);
    }

    public List<SpecialRequest> convertToList(List<Integer> ids){
        List<SpecialRequest> specialRequests = new ArrayList<SpecialRequest>();

        for(Integer id : ids){
            SpecialRequest specialRequest = specialRequestsRepository.findSpecialRequestById(id);
            if ( specialRequest == null)
                throw new ApiException("ID not found",404);
            specialRequests.add(specialRequest);
        }

        return specialRequests;
    }

    public float calculatePrice(List<SpecialRequest> list){

        float totalPrice = 0;
        for (SpecialRequest specialRequest : list)
            totalPrice += specialRequest.getPrice();

        return totalPrice;
    }


}
