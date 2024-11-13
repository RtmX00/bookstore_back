package com.example.test.mapper;

import com.example.test.dal.Orders;
import com.example.test.dto.order.CreateUpdateOrderDto;
import com.example.test.dto.order.ResponseOrderDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T12:41:16+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Orders toEntity(CreateUpdateOrderDto model) {
        if ( model == null ) {
            return null;
        }

        Orders orders = new Orders();

        orders.setUsers( model.getUsers() );
        orders.setName( model.getName() );
        orders.setNumber( model.getNumber() );
        orders.setPrice( model.getPrice() );
        orders.setStatus( model.getStatus() );
        orders.setTotalPrice( model.getTotalPrice() );

        return orders;
    }

    @Override
    public ResponseOrderDto toDto(Orders model) {
        if ( model == null ) {
            return null;
        }

        ResponseOrderDto responseOrderDto = new ResponseOrderDto();

        responseOrderDto.setCreateAt( model.getCreateAt() );
        responseOrderDto.setUpdatedAt( model.getUpdatedAt() );
        responseOrderDto.setId( model.getId() );
        responseOrderDto.setName( model.getName() );
        responseOrderDto.setNumber( model.getNumber() );
        responseOrderDto.setPrice( model.getPrice() );
        responseOrderDto.setStatus( model.getStatus() );
        responseOrderDto.setTotalPrice( model.getTotalPrice() );

        return responseOrderDto;
    }
}
