package com.example.test.dto.Favorite;

import com.example.test.dal.Products;
import com.example.test.dal.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTopFavoriteAdminDto {
    private Products products;
    private Long count;
}
