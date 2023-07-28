package com.example.shophaven.service;

import com.example.shophaven.dto.request.CheckOutRequestDto;
import com.example.shophaven.dto.request.ItemRequestDto;
import com.example.shophaven.dto.response.CustomerResponseDto;
import com.example.shophaven.dto.response.ItemResponseDto;
import com.example.shophaven.dto.response.OrderResponseDto;
import com.example.shophaven.exception.CustomerNotFoundException;
import com.example.shophaven.exception.EmptyCartException;
import com.example.shophaven.exception.InsufficientQuantityException;
import com.example.shophaven.exception.ProductNotFoundException;
import com.example.shophaven.model.Cart;
import com.example.shophaven.model.Customer;
import com.example.shophaven.model.Item;
import com.example.shophaven.model.Product;
import com.example.shophaven.repository.CartRepository;
import com.example.shophaven.repository.CustomerRepository;
import com.example.shophaven.repository.ItemRepository;
import com.example.shophaven.repository.ProductRepository;
import com.example.shophaven.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderService orderService;


    public ItemResponseDto addItem(ItemRequestDto itemRequestDto) throws Exception {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }

        Product product = productOptional.get();

        if(product.getAvailableQuantity()<itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Requested Quantity not available");
        }
        Cart cart = customer.getCart();


        Item item = new Item();
        item.setRequiredQuantity(itemRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity()*product.getPrice());


       Item saveditem = itemRepository.save(item);


        cartRepository.save(cart);

       return ItemTransformer.itemToItemResponseDto(saveditem);

    }

    public OrderResponseDto checkout(CheckOutRequestDto checkOutRequestDto) {

        Customer customer = customerRepository.findByEmailId(checkOutRequestDto.getCustomerEmail());

        if(customer==null){
            throw new CustomerNotFoundException("EmailId doesn't Exist");
        }

        Cart cart = customer.getCart();

        if(cart==null || cart.getItems().isEmpty()){
            throw new EmptyCartException("Cannot checkout an empty cart");
        }

        OrderResponseDto orderResponseDto = orderService.placeAnOrder(cart, checkOutRequestDto.getCardNo());
        cart.getItems().clear();
        cart.setCartTotal(0);

        customer.setCart(null);
        customerRepository.save(customer);

        return orderResponseDto;


    }
}
