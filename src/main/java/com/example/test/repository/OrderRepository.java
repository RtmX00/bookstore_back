package com.example.test.repository;

import com.example.test.dal.Orders;
import com.example.test.dal.Users;
import com.example.test.enums.OrderStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID>, CrudRepository<Orders, UUID> {
    Orders findFirstByUsersAndStatus(Users users, OrderStatus status);
    List<Orders> findByUsers(Users users , Pageable pageable);
    @Query("SELECT u FROM Orders u WHERE :name IS NULL OR u.name LIKE %:name%")
    List<Orders> findByNameContainingOrAll(@Param("name") String name, Pageable pageable);
}
