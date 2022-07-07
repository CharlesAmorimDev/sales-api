package org.example.ecommerce.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Pedido não encontrado.");
    }
}
