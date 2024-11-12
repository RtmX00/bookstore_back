package com.example.test.repository;



import com.example.test.dal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<Users, UUID>, CrudRepository<Users, UUID> {
    Optional<Users> findByUsername(String username);

    //List<Users> findAllBy(Pageable pageable);
    Optional<Users> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM Users u WHERE :username IS NULL OR u.username LIKE %:username%")
    List<Users> findByUsernameContainingOrAll(@Param("username") String username, Pageable pageable);
}


