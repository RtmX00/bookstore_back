package com.example.test.mapper;

import com.example.test.dal.Orders;
import com.example.test.dto.order.CreateUpdateOrderDto;
import com.example.test.dto.order.ResponseOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Orders toEntity (CreateUpdateOrderDto model);
    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseOrderDto toDto (Orders model);
}
