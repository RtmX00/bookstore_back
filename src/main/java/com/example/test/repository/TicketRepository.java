package com.example.test.repository;

import com.example.test.dal.Products;
import com.example.test.dal.Ticket;
import com.example.test.dal.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository  extends JpaRepository<Ticket, UUID>, CrudRepository<Ticket,UUID> {
    List<Ticket> findByUsers(Users users, Pageable pageable);
    @Query("SELECT DISTINCT p.To FROM Ticket p")
    List<Ticket> findDistinctUsers(Pageable pageable);
}
