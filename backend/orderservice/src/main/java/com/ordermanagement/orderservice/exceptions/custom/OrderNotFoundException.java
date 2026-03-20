package com.ordermanagement.orderservice.exceptions.custom;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException(String messages){
        super(messages);
    }
}
