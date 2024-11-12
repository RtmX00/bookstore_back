package com.example.test.service.Favorite;

import com.example.test.dal.Favorite;
import com.example.test.dal.Products;
import com.example.test.dal.Users;
import com.example.test.dto.Favorite.*;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.mapper.FavoriteMapper;
import com.example.test.mapper.ProductMapper;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.FavoriteRepository;
import com.example.test.repository.ProductsRepository;
import com.example.test.repository.UserRepository;
import com.example.test.utils.FileUtil;
import com.example.test.utils.ImageFolderProperties;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final FileUtil fileUtil;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;
    private final FavoriteMapper favoriteMapper = Mappers.getMapper(FavoriteMapper.class);
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    public FavoriteService(
            FavoriteRepository favoriteRepository, FileUtil fileUtil,
            UserRepository userRepository,
            ProductsRepository productsRepository,
            CategoryRepository categoryRepository
    ) {
        this.favoriteRepository = favoriteRepository;
        this.fileUtil = fileUtil;
        this.userRepository = userRepository;
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResultDto<ResponseCuntFavoriteAdminDto> getList(UUID productId) {
        try {
            Products product = productsRepository.findById(productId).orElseThrow(
                    () -> new CustomException.BadRequest("product not found")
            );
            var favoriteCunt = favoriteRepository.countByProducts(product);
            ResponseCuntFavoriteAdminDto favoriteDto = new ResponseCuntFavoriteAdminDto();
            favoriteDto.setCunt(favoriteCunt);
            favoriteDto.setProduct(productMapper.toDto(product));
            return ResultUtil.success(favoriteDto);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
public ResultDto<ResponseCuntFavoriteDto> getFavoriteByproductId(UUID productId , UUID userId){
        try{
          var user = userRepository.findById(userId).orElseThrow(
                  () -> new CustomException.BadRequest("user not found")
          );
          var product = productsRepository.findById(productId).orElseThrow(
                  () -> new CustomException.BadRequest("product not found")
          );
          var favorite = favoriteRepository.findByUserAndProducts(user, product);
          ResponseCuntFavoriteDto favoriteDto =new ResponseCuntFavoriteDto();
          if(favorite == null){
              favoriteDto.setResponseProductDto(productMapper.toDto(product));
              favoriteDto.setFavorite(false);
              return ResultUtil.success(favoriteDto);
          }else {
              favoriteDto.setResponseProductDto(productMapper.toDto(product));
              favoriteDto.setFavorite(true);
              return ResultUtil.success(favoriteDto);
          }
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
            if (page <= 1 && pageSize <= 1) {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }

            Pageable pageable = PageRequest.of(page - 1, pageSize);
            Users user = userRepository.findById(userId).orElseThrow(
                    () -> new CustomException.BadRequest("User not found")
            );
            List<ResponseFavoriteDto> favorite = favoriteRepository
                    .findByUser(user, pageable)
                    .stream()
                    .map(favoriteMapper::toDto)
                    .toList();
            favorite.forEach(item ->
                    item.
                            getProducts().
                            setImage(
                                    host +
                                            "/" +
                                            fileUtil.
                                                    getImage(
                                                            item.
                                                                    getProducts().
                                                                    getId().
                                                                    toString(),
                                                            ImageFolderProperties.
                                                                    productFolder)
                            )
            );

            var totalPage = (long) Math.ceil((double) favoriteRepository.count() / pageSize);
            return ResultUtil.success(new ResultPagedDto(page, pageSize, totalPage, favorite));

        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResponseCuntFavoriteAllAdminDto> getCountAdminFavoriteByCategory(
            UUID categoryId
    ) {
        var category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CustomException.BadRequest("category not found")
        );
        var products = productsRepository.findByCategory(category);
        var result = new ArrayList<ResponseCuntFavoriteAdminDto>();
        for (Products product : products) {
            var favoriteCunt = favoriteRepository.countByProducts(product);
            ResponseCuntFavoriteAdminDto favoriteDto = new ResponseCuntFavoriteAdminDto();
            favoriteDto.setCunt(favoriteCunt);
            favoriteDto.setProduct(productMapper.toDto(product));
            result.add(favoriteDto);
        }

        Map<ResponseCuntFavoriteAdminDto, Long> frequencyMap = result.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        result.sort((e1, e2) -> Long.compare(frequencyMap.get(e2), frequencyMap.get(e1)));
        var totalCount = result.size();
        return ResultUtil.success(new ResponseCuntFavoriteAllAdminDto(result, totalCount));
    }

    public ResultDto<Object> getTopProductFavorite() {
        try {
            var favoriteCunt = favoriteRepository.countAllTopFavorite();
            var result = new ArrayList<ResponseTopFavoriteAdminDto>();
            for (Object[] item : favoriteCunt) {
                result.add(new ResponseTopFavoriteAdminDto((Products) item[0], (Long) item[1]));
            }
            return ResultUtil.success(result);
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

