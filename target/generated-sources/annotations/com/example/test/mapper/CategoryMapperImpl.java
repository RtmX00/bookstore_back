package com.example.test.mapper;

import com.example.test.dal.Category;
import com.example.test.dto.category.CreateUpdateCategoryDto;
import com.example.test.dto.category.ResponseCategoryDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T09:43:59+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CreateUpdateCategoryDto model) {
        if ( model == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( model.getName() );

        return category;
    }

    @Override
    public ResponseCategoryDto toDto(Category model) {
        if ( model == null ) {
            return null;
        }

        ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto();

        responseCategoryDto.setCreateAt( model.getCreateAt() );
        responseCategoryDto.setUpdatedAt( model.getUpdatedAt() );
        responseCategoryDto.setId( model.getId() );
        responseCategoryDto.setName( model.getName() );

        return responseCategoryDto;
    }
}
