package com.example.test.mapper;

import com.example.test.dal.Favorite;
import com.example.test.dto.Favorite.CreatUpdateFavoriteDto;
import com.example.test.dto.Favorite.ResponseFavoriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FavoriteMapper {
    Favorite toEntity (CreatUpdateFavoriteDto model);
    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseFavoriteDto toDto (Favorite model);
}
