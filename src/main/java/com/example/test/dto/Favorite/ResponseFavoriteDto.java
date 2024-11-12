package com.example.test.dto.Favorite;

import com.example.test.dal.Products;
import com.example.test.dal.Users;
import com.example.test.dto.product.ResponseProductDto;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseFavoriteDto {
    private Users user;
    private ResponseProductDto products;
    private Timestamp createAt;
    private Timestamp  updatedAt;
}
