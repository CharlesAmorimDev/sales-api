package org.example.sales.controller;

import org.example.sales.dto.OrderDetailsDTO;
import org.example.sales.model.Order;
import org.example.sales.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("{id}")
    public OrderDetailsDTO orderInformations(@PathVariable Long id) {
        return service.orderInformations(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order register(@RequestBody @Valid OrderDetailsDTO orderDetailsDTO) {
        return service.generateOrder(orderDetailsDTO);


    }

}
