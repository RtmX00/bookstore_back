package com.example.test.mapper;

import com.example.test.dal.News;
import com.example.test.dto.News.CreateUpdateNewsDto;
import com.example.test.dto.News.ResponseNewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper {
    News toEntity (CreateUpdateNewsDto model);
    @Mapping(target = "createAt", source = "createAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ResponseNewsDto toDto (News model);
}
