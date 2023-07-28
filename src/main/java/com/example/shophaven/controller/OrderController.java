package com.example.shophaven.controller;


import com.example.shophaven.dto.request.OrderRequestDto;
import com.example.shophaven.dto.response.OrderResponseDto;
import com.example.shophaven.exception.CustomerNotFoundException;
import com.example.shophaven.exception.ProductNotFoundException;
import com.example.shophaven.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity orderItem(@RequestBody OrderRequestDto orderRequestDto){

        try {
            OrderResponseDto orderResponseDto = orderService.placeAnOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
           System.out.println(e.getMessage());
            return new ResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
