package com.example.test.mapper;

import com.example.test.dal.Favorite;
import com.example.test.dal.Ticket;
import com.example.test.dto.Favorite.CreatUpdateFavoriteDto;
import com.example.test.dto.Favorite.ResponseFavoriteDto;
import com.example.test.dto.Ticket.CreateUpdateTicketDto;
import com.example.test.dto.Ticket.ResponseTicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {
    Ticket toEntity (CreateUpdateTicketDto model);
    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseTicketDto toDto (Ticket model);
}
