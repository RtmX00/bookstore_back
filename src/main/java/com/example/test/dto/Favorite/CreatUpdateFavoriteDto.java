package com.example.test.dto.Favorite;

import com.example.test.dal.Products;
import com.example.test.dal.Users;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class CreatUpdateFavoriteDto {
    private UUID Id;
    private Users user;
    private Products products;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
