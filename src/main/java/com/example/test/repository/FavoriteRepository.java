package com.example.test.repository;

import com.example.test.dal.Favorite;
import com.example.test.dal.Products;
import com.example.test.dal.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface FavoriteRepository   extends JpaRepository<Favorite, UUID>, CrudRepository<Favorite,UUID> {
    Favorite findByUserAndProducts(Users user, Products products);
    List<Favorite> findByUser(Users user , Pageable pageable);
    long countByProducts(Products products);

    @Query("SELECT f.products, COUNT(f) as count FROM Favorite f GROUP BY f.products ORDER BY count DESC")
    List<Object[]> countAllTopFavorite();



}
