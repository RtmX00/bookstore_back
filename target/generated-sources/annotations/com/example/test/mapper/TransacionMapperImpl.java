package com.example.test.mapper;

import com.example.test.dal.Transaction;
import com.example.test.dto.Transaction.CreatInsertTransactionDto;
import com.example.test.dto.Transaction.ResponseTransactionDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T09:43:59+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class TransacionMapperImpl implements TransacionMapper {

    @Override
    public Transaction toEntity(CreatInsertTransactionDto model) {
        if ( model == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setOrders( model.getOrders() );
        transaction.setStatus( model.getStatus() );
        transaction.setTrackingNumber( model.getTrackingNumber() );

        return transaction;
    }

    @Override
    public ResponseTransactionDto toDto(Transaction model) {
        if ( model == null ) {
            return null;
        }

        ResponseTransactionDto responseTransactionDto = new ResponseTransactionDto();

        responseTransactionDto.setCreateAt( model.getCreateAt() );
        responseTransactionDto.setUpdatedAt( model.getUpdatedAt() );
        responseTransactionDto.setId( model.getId() );
        responseTransactionDto.setOrders( model.getOrders() );
        responseTransactionDto.setStatus( model.getStatus() );
        responseTransactionDto.setTrackingNumber( model.getTrackingNumber() );

        return responseTransactionDto;
    }
}
