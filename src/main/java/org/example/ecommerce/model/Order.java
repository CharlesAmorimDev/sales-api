package org.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.ecommerce.annotation.NotEmptyList;
import org.example.ecommerce.enums.OrderStatus;

import javax.persistence.*;

@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JsonIgnore
    @OneToOne
    private OrderDetails orderDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}

