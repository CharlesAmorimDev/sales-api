package org.example.ecommerce.repository;

import org.example.ecommerce.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
