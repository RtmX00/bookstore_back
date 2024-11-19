package com.example.test.repository;


import com.example.test.dal.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID>, CrudRepository<Ticket, UUID> {

}
