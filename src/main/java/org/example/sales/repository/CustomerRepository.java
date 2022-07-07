package org.example.sales.repository;

import org.example.sales.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
/*
    List<Customer> findByNameLike(String name);

    @Query(value = " select c from Customer c where c.name like :name") //Query HQL
    List<Customer> encontrarPorNomeHQL(@Param("name") String name);

    @Query(value = " select * from Customer c where c.name like '%:name%'", nativeQuery = true) //Query SQL Nativo
    List<Customer> encontrarPorNomeSQLNATIVO(@Param("name") String name);

    @Query(value = " delete from Customer c where c.name = :name")
    @Modifying
    void deleteByName(String name);

    @Query(" select c from Customer c left join fetch c.order where c.id = :id")
    Customer findCustomertFetchOrder(@Param("id") Integer id);
*/
    Optional<Customer> findByUsername(String username);


}
