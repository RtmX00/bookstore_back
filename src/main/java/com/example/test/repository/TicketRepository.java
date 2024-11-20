package com.example.test.repository;


import com.example.test.dal.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID>, CrudRepository<Ticket, UUID> {
    @Query("SELECT DISTINCT t FROM Ticket t ORDER BY t.createAt DESC")
    List<Ticket> findDistinctFromOrderedByCreatedAt(Pageable pageable);
    List<Ticket> findByFromIdAndToId(UUID fromId, UUID toId , Pageable pageable);

}
