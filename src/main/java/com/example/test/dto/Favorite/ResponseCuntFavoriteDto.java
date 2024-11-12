package com.example.test.dto.Favorite;

import com.example.test.dto.product.ResponseProductDto;
import lombok.Data;

@Data
public class ResponseCuntFavoriteDto {
    private ResponseProductDto responseProductDto;
    private Boolean favorite;
}
