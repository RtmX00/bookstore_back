package com.example.test.repository;

import com.example.test.dal.Category;
import com.example.test.dal.Favorite;
import com.example.test.dal.Products;
import com.example.test.dal.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Repository
public interface ProductsRepository extends JpaRepository<Products, UUID>, CrudRepository<Products,UUID>{
    List<Products> findByCategory(Category category);
    List<Products> findByCategoryId(UUID categoryId);
    @Query("SELECT u FROM Products u WHERE :name IS NULL OR u.name LIKE %:name%")
    List<Products> findByNameContainingOrAll(@Param("name") String name, Pageable pageable);


}
