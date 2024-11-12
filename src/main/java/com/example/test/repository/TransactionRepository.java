package com.example.test.repository;

import com.example.test.dal.Transaction;
import com.example.test.dal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>, CrudRepository<Transaction,UUID> {
    List<Transaction> findByUser(Users user);
}
