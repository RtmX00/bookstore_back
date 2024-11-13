package com.example.test.mapper;

import com.example.test.dal.Category;
import com.example.test.dal.Products;
import com.example.test.dto.category.ResponseCategoryDto;
import com.example.test.dto.product.CreateUpdateProductDto;
import com.example.test.dto.product.ResponseProductDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T12:41:16+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Products toEntity(CreateUpdateProductDto model) {
        if ( model == null ) {
            return null;
        }

        Products products = new Products();

        products.setName( model.getName() );
        products.setPrice( model.getPrice() );
        products.setNameAuthor( model.getNameAuthor() );
        products.setPublicationDate( model.getPublicationDate() );
        products.setDescription( model.getDescription() );

        return products;
    }

    @Override
    public ResponseProductDto toDto(Products model) {
        if ( model == null ) {
            return null;
        }

        ResponseProductDto responseProductDto = new ResponseProductDto();

        responseProductDto.setCreateAt( model.getCreateAt() );
        responseProductDto.setUpdatedAt( model.getUpdatedAt() );
        responseProductDto.setId( model.getId() );
        responseProductDto.setName( model.getName() );
        responseProductDto.setPrice( model.getPrice() );
        responseProductDto.setDescription( model.getDescription() );
        responseProductDto.setNameAuthor( model.getNameAuthor() );
        responseProductDto.setPublicationDate( model.getPublicationDate() );
        responseProductDto.setCondition( model.getCondition() );
        responseProductDto.setCategory( categoryToResponseCategoryDto( model.getCategory() ) );

        return responseProductDto;
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
}
