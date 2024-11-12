package com.example.test.mapper;

import com.example.test.dal.Users;
import com.example.test.dto.user.CreateUpdateUserDto;
import com.example.test.dto.user.ResponseUserDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    Users toEntity(CreateUpdateUserDto model);
    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseUserDto toDto(Users model);


   /* @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(RequestRegisterUserDto model, @MappingTarget User user);*/

}
