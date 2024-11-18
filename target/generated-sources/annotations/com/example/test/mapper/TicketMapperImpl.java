package com.example.test.mapper;

import com.example.test.dal.Ticket;
import com.example.test.dto.Ticket.CreateUpdateTicketDto;
import com.example.test.dto.Ticket.ResponseTicketDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T09:43:59+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class TicketMapperImpl implements TicketMapper {

    @Override
    public Ticket toEntity(CreateUpdateTicketDto model) {
        if ( model == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setCreateAt( model.getCreateAt() );
        ticket.setUpdatedAt( model.getUpdatedAt() );
        ticket.setId( model.getId() );
        ticket.setFrom( model.getFrom() );
        ticket.setTo( model.getTo() );
        ticket.setContent( model.getContent() );

        return ticket;
    }

    @Override
    public ResponseTicketDto toDto(Ticket model) {
        if ( model == null ) {
            return null;
        }

        ResponseTicketDto responseTicketDto = new ResponseTicketDto();

        responseTicketDto.setCreateAt( model.getCreateAt() );
        responseTicketDto.setUpdatedAt( model.getUpdatedAt() );
        responseTicketDto.setId( model.getId() );
        responseTicketDto.setFrom( model.getFrom() );
        responseTicketDto.setTo( model.getTo() );
        responseTicketDto.setContent( model.getContent() );

        return responseTicketDto;
    }
}
