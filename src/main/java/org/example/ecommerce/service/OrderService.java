package org.example.ecommerce.service;

import org.example.ecommerce.dto.OrderDetailsDTO;
import org.example.ecommerce.enums.OrderStatus;
import org.example.ecommerce.exception.BusinessRuleException;
import org.example.ecommerce.model.Customer;
import org.example.ecommerce.model.Order;
import org.example.ecommerce.model.OrderDetails;
import org.example.ecommerce.model.Product;
import org.example.ecommerce.repository.CustomerRepository;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.repository.OrdersDetailsRepository;
import org.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrdersDetailsRepository ordersDetailsRepository;

    @Transactional
    public Order generateOrder(OrderDetailsDTO orderDetailsDTO) {
        Customer customer = verifyCustomer(orderDetailsDTO.getUsername());
        List<Product> products = verifyProductList(orderDetailsDTO.getProducts());

        BigDecimal total = BigDecimal.ZERO;

        total = products.stream()
                .map(Product :: getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setDate(LocalDate.now());
        orderDetails.setTotal(total);
        orderDetails.setCustomer(customer);
        orderDetails.setProductList(products);

        Order order = new Order();
        order.setOrderDetails(orderDetails);
        order.setStatus(OrderStatus.GENERATED);
        orderRepository.save(order);

        orderDetails.setOrder(order);
        ordersDetailsRepository.save(orderDetails);

        return order;
    }

    public OrderDetailsDTO orderInformations(Long id) {
        OrderDetails orderDetails = ordersDetailsRepository
                .findById(id)
                .orElseThrow( ()-> new BusinessRuleException("Pedido não encontado"));

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

        orderDetailsDTO.setUsername(orderDetails.getCustomer().getUsername());
        orderDetailsDTO.setAddress(orderDetails.getCustomer().getAddress());
        orderDetailsDTO.setNumberOrder(orderDetails.getId());
        orderDetailsDTO.setStatus(orderDetails.getOrder().getStatus());
        orderDetailsDTO.setDateOrder(orderDetails.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        orderDetailsDTO.setProducts(orderDetails.getProductList());
        orderDetailsDTO.setTotalOrder(orderDetails.getTotal());

        return orderDetailsDTO;
    }

    public void updateStatus(Integer orderID, String newStatus) {

    }

    private Customer verifyCustomer(String username) {
        return customerRepository
                .findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    private List<Product> verifyProductList(List<Product> productList) {
        if(productList.isEmpty()) {
            throw new BusinessRuleException("O carrinho está vazio");
        }
        return productList
                .stream()
                .map(products -> {
                    Product product = productRepository
                            .findById(products.getId())
                            .orElseThrow( () -> new BusinessRuleException("Produto não encontrado."));
                    product.setAmount(products.getAmount());
                    return product;
                }).collect(Collectors.toList());
    }

}
