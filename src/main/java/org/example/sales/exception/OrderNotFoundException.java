package org.example.sales.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Pedido não encontrado.");
    }
}
