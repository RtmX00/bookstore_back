package com.example.test.repository;


import com.example.test.dal.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NewsRepository extends JpaRepository<News, UUID>, CrudRepository<News,UUID> {
}
