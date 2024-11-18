package com.example.test.mapper;

import com.example.test.dal.Category;
import com.example.test.dal.Favorite;
import com.example.test.dal.Products;
import com.example.test.dto.Favorite.CreatUpdateFavoriteDto;
import com.example.test.dto.Favorite.ResponseFavoriteDto;
import com.example.test.dto.category.ResponseCategoryDto;
import com.example.test.dto.product.ResponseProductDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T09:43:59+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class FavoriteMapperImpl implements FavoriteMapper {

    @Override
    public Favorite toEntity(CreatUpdateFavoriteDto model) {
        if ( model == null ) {
            return null;
        }

        Favorite favorite = new Favorite();

        favorite.setId( model.getId() );
        favorite.setUser( model.getUser() );
        favorite.setProducts( model.getProducts() );
        favorite.setCreateAt( model.getCreateAt() );
        favorite.setUpdatedAt( model.getUpdatedAt() );

        return favorite;
    }

    @Override
    public ResponseFavoriteDto toDto(Favorite model) {
        if ( model == null ) {
            return null;
        }

        ResponseFavoriteDto responseFavoriteDto = new ResponseFavoriteDto();

        responseFavoriteDto.setCreateAt( model.getCreateAt() );
        responseFavoriteDto.setUpdatedAt( model.getUpdatedAt() );
        responseFavoriteDto.setUser( model.getUser() );
        responseFavoriteDto.setProducts( productsToResponseProductDto( model.getProducts() ) );

        return responseFavoriteDto;
    }

    protected ResponseCategoryDto categoryToResponseCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto();

        responseCategoryDto.setId( category.getId() );
        responseCategoryDto.setName( category.getName() );
        responseCategoryDto.setCreateAt( category.getCreateAt() );
        responseCategoryDto.setUpdatedAt( category.getUpdatedAt() );

        return responseCategoryDto;
    }

    protected ResponseProductDto productsToResponseProductDto(Products products) {
        if ( products == null ) {
            return null;
        }

        ResponseProductDto responseProductDto = new ResponseProductDto();

        responseProductDto.setId( products.getId() );
        responseProductDto.setName( products.getName() );
        responseProductDto.setPrice( products.getPrice() );
        responseProductDto.setDescription( products.getDescription() );
        responseProductDto.setNameAuthor( products.getNameAuthor() );
        responseProductDto.setPublicationDate( products.getPublicationDate() );
        responseProductDto.setCondition( products.getCondition() );
        responseProductDto.setCategory( categoryToResponseCategoryDto( products.getCategory() ) );
        responseProductDto.setCreateAt( products.getCreateAt() );
        responseProductDto.setUpdatedAt( products.getUpdatedAt() );

        return responseProductDto;
    }
}
