package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.Model.SpecialRequest;
import com.example.javabootcampfinalproject.Repository.SpecialRequestsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpecialRequestServiceTest {
    @InjectMocks
    SpecialRequestsService specialRequestsService;
    @Mock
    SpecialRequestsRepository specialRequestsRepository;
    
    
    SpecialRequest specialRequest1;
    SpecialRequest specialRequest2;
    List<SpecialRequest> specialRequestsList;


    @BeforeEach
    void setUp(){
        specialRequest1 = new SpecialRequest(1,"Sort items",150,null,null);
        specialRequest2 = new SpecialRequest(2,"Periodic order",200,5,null);
        specialRequestsList = new ArrayList<SpecialRequest>();
        specialRequestsList.add(specialRequest1);
        specialRequestsList.add(specialRequest2);
    }

    @Test
    public void getSpecialRequestTest(){
        when(specialRequestsRepository.findSpecialRequestById(specialRequest1.getId())).thenReturn(specialRequest1);

        SpecialRequest specialRequest = specialRequestsService.getSpecialRequest(specialRequest1.getId());
        Assertions.assertEquals(specialRequest1,specialRequest);
        verify(specialRequestsRepository,times(1)).findSpecialRequestById(specialRequest1.getId());
    }

    @Test
    public void getAllTodoTest(){
        when(specialRequestsRepository.findAll()).thenReturn(specialRequestsList);
        List<SpecialRequest> specialRequests=specialRequestsService.getAllSpecialRequests();
        Assertions.assertEquals(2,specialRequests.size());
        verify(specialRequestsRepository,times(1)).findAll();

    }

    @Test
    public void addSpecialRequestTest(){
        specialRequestsService.addSpecialRequest(specialRequest1);
        verify(specialRequestsRepository,times(1)).save(specialRequest1);
    }

    @Test void deleteSpecialRequest(){
        when(specialRequestsRepository.findSpecialRequestById(specialRequest1.getId())).thenReturn(specialRequest1);

        specialRequestsService.deleteSpecialRequest(specialRequest1.getId());
        verify(specialRequestsRepository,times(1)).findSpecialRequestById(specialRequest1.getId());
        verify(specialRequestsRepository,times(1)).delete(specialRequest1);
    }
}
