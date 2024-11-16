package com.example.test.mapper;

import com.example.test.dal.Category;
import com.example.test.dal.Ticket;
import com.example.test.dto.Ticket.CreatUpdateTicketDto;
import com.example.test.dto.Ticket.ResponseTicketDto;
import com.example.test.dto.category.CreateUpdateCategoryDto;
import com.example.test.dto.category.ResponseCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {
    Ticket toEntity(CreatUpdateTicketDto model);

    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseTicketDto toDto(Ticket model);
}
