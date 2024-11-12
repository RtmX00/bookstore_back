package com.example.test.mapper;

import com.example.test.dal.Products;
import com.example.test.dto.product.CreateUpdateProductDto;
import com.example.test.dto.product.ResponseProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Products toEntity(CreateUpdateProductDto model);

    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseProductDto toDto(Products model);
}
