package org.example.sales.repository;

import org.example.sales.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
/*
    List<Order> findByCustomer(Customer customer);
    @Query(" SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails where o.id = :id")
    Optional<Order> findByIdFetchItems(@Param("id") Long id);
*/

}
