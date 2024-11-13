package com.example.test.mapper;

import com.example.test.dal.News;
import com.example.test.dto.News.CreateUpdateNewsDto;
import com.example.test.dto.News.ResponseNewsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T12:41:16+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class NewsMapperImpl implements NewsMapper {

    @Override
    public News toEntity(CreateUpdateNewsDto model) {
        if ( model == null ) {
            return null;
        }

        News news = new News();

        news.setTitle( model.getTitle() );
        news.setContent( model.getContent() );

        return news;
    }

    @Override
    public ResponseNewsDto toDto(News model) {
        if ( model == null ) {
            return null;
        }

        ResponseNewsDto responseNewsDto = new ResponseNewsDto();

        responseNewsDto.setCreateAt( model.getCreateAt() );
        responseNewsDto.setUpdatedAt( model.getUpdatedAt() );
        responseNewsDto.setTitle( model.getTitle() );
        responseNewsDto.setContent( model.getContent() );

        return responseNewsDto;
    }
}
