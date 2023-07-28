package com.example.shophaven.service;


import com.example.shophaven.Enum.ProductStatus;
import com.example.shophaven.dto.request.OrderRequestDto;
import com.example.shophaven.dto.response.ItemResponseDto;
import com.example.shophaven.dto.response.OrderResponseDto;
import com.example.shophaven.exception.*;
import com.example.shophaven.model.*;
import com.example.shophaven.repository.*;
import com.example.shophaven.transformer.CardTransformer;
import com.example.shophaven.transformer.ItemTransformer;
import com.example.shophaven.transformer.OrderTransformer;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderEntityRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;





    public String cancelOrder(String orderId){

        OrderEntity order = orderRepository.findByOrderId(orderId);

        if(order==null)
            throw new OrderNotFoundException("order not found");

        order.setCustomer(null);
        for(Item item: order.getItems()){
            item.setOrderEntity(null);
        }
        orderRepository.save(order);
        orderRepository.delete(order);

        return  "Order Deleted SuccessFully";


    }
    private Product checkProduct(Product product, int requiredQuantity)  {


        if(product.getAvailableQuantity()<requiredQuantity){
            throw new InsufficientQuantityException("Requested Quantity not available");
        }
        return product;
    }

    private Card findCard(String cardNo, List<Card> cardList)  {

        Card currCard = null;
        for(Card card: cardList){
            if(card.getCardNo().equals(cardNo)){
                currCard =card;
            }
        }
        if(currCard==null){
            throw new CardNotFoundException("Card not found");
        }
        if(currCard.getValidTill().before(new Date())){
            throw new InvalidCardException("The card has expired");
        }
        return currCard;
    }
    public OrderResponseDto placeAnOrder(OrderRequestDto orderRequestDto) {


        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        Optional<Product> productOptional = productRepository.findById(orderRequestDto.getProductId());

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }

        Product product = checkProduct(productOptional.get(), orderRequestDto.getRequiredQuantity());

        Card card = findCard(orderRequestDto.getCardUsed(),customer.getCards());



        OrderEntity order = OrderTransformer.orderRequestDtoToOrder(orderRequestDto);
        order.setOrderTotal(product.getPrice()*orderRequestDto.getRequiredQuantity());


        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrderEntity(order);

        order.setCustomer(customer);
        order.getItems().add(item);

//
//
//
//        Item savedItem = itemRepository.save(item);
//
//
//
        product.setAvailableQuantity(product.getAvailableQuantity()- item.getRequiredQuantity());


        if(product.getAvailableQuantity()==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }


        OrderEntity savedOrder = orderRepository.save(order);

//
        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));



//
//
//
        OrderResponseDto orderResponseDto = OrderTransformer.orderToOrderResponseDto(savedOrder);
        orderResponseDto.setCardUsed(CardTransformer.maskCardNo(card.getCardNo()));
        Item saveditem = savedOrder.getItems().get(0);

        // convert items to item respone dtos
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        itemResponseDtoList.add(ItemTransformer.itemToItemResponseDto(saveditem));
        orderResponseDto.setItemsList(itemResponseDtoList);


        return orderResponseDto;

    }

    public OrderResponseDto placeAnOrder(Cart cart, String cardNo) {

        Card card =  findCard(cardNo, cart.getCustomer().getCards());

        OrderEntity order = new OrderEntity();

        int orderTotal = 0;

        for(Item item: cart.getItems()){ // check available quantity for all items and get order total
            Product product = checkProduct(item.getProduct(),item.getRequiredQuantity());
            orderTotal+=product.getPrice()*item.getRequiredQuantity();

        }


        for(Item item: cart.getItems()){ // add items to order and update product quantity
            Product product = item.getProduct();
            product.setAvailableQuantity(product.getAvailableQuantity()- item.getRequiredQuantity());

            if(product.getAvailableQuantity()==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            productRepository.save(product); // save changes to the product

            item.setCart(null); // removed from cart
            item.setOrderEntity(order); // added to order

            order.getItems().add(item);

        }
        order.setCustomer(cart.getCustomer());
        order.setCardUsed(cardNo);
        order.setOrderTotal(orderTotal);
        order.setOrderId(UUID.randomUUID().toString());

        OrderEntity savedOrder = orderRepository.save(order);

        OrderResponseDto orderResponseDto = OrderTransformer.orderToOrderResponseDto(savedOrder);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : savedOrder.getItems()){
            itemResponseDtoList.add(ItemTransformer.itemToItemResponseDto(item));
        }
        orderResponseDto.setItemsList(itemResponseDtoList);
        return orderResponseDto;

    }

}
