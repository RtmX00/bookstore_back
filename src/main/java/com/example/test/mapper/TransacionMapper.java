package com.example.test.mapper;

import com.example.test.dal.Transaction;
import com.example.test.dto.Transaction.CreatInsertTransactionDto;
import com.example.test.dto.Transaction.ResponseTransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransacionMapper {
    Transaction toEntity(CreatInsertTransactionDto model);
    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseTransactionDto toDto(Transaction model);
}
