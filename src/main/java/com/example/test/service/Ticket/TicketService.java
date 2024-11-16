package com.example.test.service.Ticket;

import com.example.test.dal.Favorite;
import com.example.test.dal.Products;
import com.example.test.dal.Users;
import com.example.test.dto.Favorite.*;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.mapper.FavoriteMapper;
import com.example.test.mapper.OrderMapper;
import com.example.test.mapper.ProductMapper;
import com.example.test.mapper.TicketMapper;
import com.example.test.repository.*;
import com.example.test.utils.FileUtil;
import com.example.test.utils.ImageFolderProperties;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {
   private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public ResultDto<ResponseCuntFavoriteAdminDto> getList(UUID productId) {
        return ResultUtil.success(null);
    }
public ResultDto<ResponseCuntFavoriteDto> getFavoriteByproductId(UUID productId , UUID userId){
        try{
            return ResultUtil.success(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
    public ResultDto<ResultPagedDto<List<ResponseFavoriteDto>>> getListClient(
            int pageSize,
            int page,
            UUID userId,
            String host
    ) {
        try {
            return ResultUtil.success(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResponseCuntFavoriteAllAdminDto> getCountAdminFavoriteByCategory(
            UUID categoryId
    ) {
        return ResultUtil.success(null);
    }

    public ResultDto<Object> getTopProductFavorite() {
        try {
            return ResultUtil.success(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<ResponseFavoriteDto> create(UUID userId, UUID productId) {
        try {
            Users user = userRepository.findById(userId).orElseThrow(
                    () -> new CustomException.BadRequest("User not found")
            );

            var products = productsRepository.findById(productId).orElseThrow(() ->
                    new CustomException.BadRequest("Product not found")
            );
            var favoriteFind = favoriteRepository.findByUserAndProducts(user, products);
            if (favoriteFind != null) {
                throw new CustomException.BadRequest("اقا شما این را لایک کردید ");
            } else {
                var favorite = new Favorite();
                favorite.setUser(user);
                favorite.setProducts(products);
                favoriteRepository.save(favorite);
                return ResultUtil.success(favoriteMapper.toDto(favorite));
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<Boolean> delete(UUID userId, UUID productId) {
        try {
            Users user = userRepository.findById(userId).orElseThrow(
                    () -> new CustomException.BadRequest("User not found")
            );

            Products products = productsRepository.findById(productId).orElseThrow(
                    () -> new CustomException.BadRequest("Product not found")
            );
            Favorite favorite = favoriteRepository.findByUserAndProducts(user, products);
            if (favorite != null) {
                favoriteRepository.delete(favorite);
                return ResultUtil.success(true);
            } else {
                throw new CustomException.BadRequest("Favorite not found");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

