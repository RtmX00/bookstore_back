package com.example.test.dto.Favorite;

import com.example.test.dto.product.ResponseProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCuntFavoriteAllAdminDto {
    private List<ResponseCuntFavoriteAdminDto> products;
    private long totalCunt;
}
