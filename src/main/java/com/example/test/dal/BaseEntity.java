package com.example.test.dal;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @CreationTimestamp
    private Timestamp createAt;
    @UpdateTimestamp
    private Timestamp  updatedAt;
}
