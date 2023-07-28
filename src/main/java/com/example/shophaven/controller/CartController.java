package com.example.shophaven.controller;


import com.example.shophaven.dto.request.CheckOutRequestDto;
import com.example.shophaven.dto.request.ItemRequestDto;
import com.example.shophaven.dto.response.ItemResponseDto;
import com.example.shophaven.dto.response.OrderResponseDto;
import com.example.shophaven.exception.CustomerNotFoundException;
import com.example.shophaven.exception.ProductNotFoundException;
import com.example.shophaven.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try {
            ItemResponseDto itemResponseDto = cartService.addItem(itemRequestDto);
            return new ResponseEntity(itemResponseDto, HttpStatus.CREATED);

        }catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @PostMapping("/checkout")
    public ResponseEntity checkOut(@RequestBody CheckOutRequestDto checkOutRequestDto){
        try {
            OrderResponseDto orderResponseDto = cartService.checkout(checkOutRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);

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
