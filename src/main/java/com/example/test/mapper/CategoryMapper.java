package com.example.test.mapper;

import com.example.test.dal.Category;
import com.example.test.dal.Products;
import com.example.test.dto.category.CreateUpdateCategoryDto;
import com.example.test.dto.category.ResponseCategoryDto;
import com.example.test.dto.product.CreateUpdateProductDto;
import com.example.test.dto.product.ResponseProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(CreateUpdateCategoryDto model);

    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseCategoryDto toDto(Category model);
}
