package com.example.test.repository;

import com.example.test.dal.Products;
import com.example.test.dal.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository  extends JpaRepository<Ticket, UUID>, CrudRepository<Ticket,UUID> {
}
