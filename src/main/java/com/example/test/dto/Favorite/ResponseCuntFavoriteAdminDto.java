package com.example.test.dto.Favorite;

import com.example.test.dto.product.ResponseProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCuntFavoriteAdminDto {
    private ResponseProductDto product;
    private long cunt;
    private String image;
}
