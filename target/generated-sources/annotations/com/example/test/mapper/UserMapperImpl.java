package com.example.test.mapper;

import com.example.test.dal.Users;
import com.example.test.dto.user.CreateUpdateUserDto;
import com.example.test.dto.user.ResponseUserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T12:41:16+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public Users toEntity(CreateUpdateUserDto model) {
        if ( model == null ) {
            return null;
        }

        Users users = new Users();

        users.setUsername( model.getUsername() );
        users.setPassword( model.getPassword() );
        users.setFullname( model.getFullname() );
        users.setEmail( model.getEmail() );
        users.setPhoneNumber( model.getPhoneNumber() );
        users.setAddress( model.getAddress() );

        return users;
    }

    @Override
    public ResponseUserDto toDto(Users model) {
        if ( model == null ) {
            return null;
        }

        ResponseUserDto responseUserDto = new ResponseUserDto();

        responseUserDto.setCreateAt( model.getCreateAt() );
        responseUserDto.setUpdatedAt( model.getUpdatedAt() );
        responseUserDto.setId( model.getId() );
        responseUserDto.setFullname( model.getFullname() );
        responseUserDto.setUsername( model.getUsername() );
        responseUserDto.setEmail( model.getEmail() );
        responseUserDto.setAddress( model.getAddress() );
        responseUserDto.setPhoneNumber( model.getPhoneNumber() );
        responseUserDto.setUserRole( model.getUserRole() );

        return responseUserDto;
    }
}
