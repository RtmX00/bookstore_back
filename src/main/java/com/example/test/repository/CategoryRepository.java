package com.example.test.repository;

import com.example.test.dal.Category;
import com.example.test.dal.Products;
import com.example.test.dto.Favorite.ResponseCuntFavoriteAdminDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>, CrudRepository<Category,UUID>{
    @Query("SELECT u FROM Category u WHERE :name IS NULL OR u.name LIKE %:name%")
    List<Category> findByNameContainingOrAll(@Param("name") String name, Pageable pageable);

}
