package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.Model.SpecialRequest;
import com.example.javabootcampfinalproject.Service.SpecialRequestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/special-request")
@RequiredArgsConstructor
public class SpecialRequestController {
    private final SpecialRequestsService specialRequestsService;


    @GetMapping("/{id}")
    public ResponseEntity getSpecialRequest(@PathVariable Integer id){
        SpecialRequest specialRequest = specialRequestsService.getSpecialRequest(id);
        return ResponseEntity.status(200).body(specialRequest);
    }

    @GetMapping("/All")
    public ResponseEntity getAllSpecialRequests(){
        List<SpecialRequest> specialRequests = specialRequestsService.getAllSpecialRequests();
        return ResponseEntity.status(200).body(specialRequests);
    }

}
