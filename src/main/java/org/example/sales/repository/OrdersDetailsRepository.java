package org.example.sales.repository;

import org.example.sales.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
