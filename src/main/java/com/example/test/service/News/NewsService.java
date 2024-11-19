package com.example.test.service.News;

import com.example.test.dal.News;
import com.example.test.dto.News.CreateUpdateNewsDto;
import com.example.test.dto.News.ResponseNewsDto;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.product.ResponseProductDto;
import com.example.test.mapper.NewsMapper;
import com.example.test.mapper.OrderMapper;
import com.example.test.repository.NewsRepository;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public ResultDto<ResultPagedDto<List<ResponseNewsDto>>> list(
            int pageSize,
            int page
    )
    {
        try {



            Pageable pageable = PageRequest.of(page-1, pageSize);
            List<ResponseNewsDto> newsList = newsRepository
                    .findAll(pageable)
                    .stream()
                    .map(newsMapper::toDto)
                    .toList();
            if (page >= 1 && pageSize>=1) {
                var totalPage = (long) Math.ceil((double) newsRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page,pageSize,totalPage,newsList));
            }else {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }


        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<ResponseNewsDto> create(CreateUpdateNewsDto model){
        try {
            if (model.getTitle() == null || model.getContent() == null){
                throw new CustomException.BadRequest("please enter title or content");
            }else {
                News news = newsMapper.toEntity(model);
                newsRepository.save(news);
                return ResultUtil.success(newsMapper.toDto(news));
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResultDto<Boolean> deleteById(UUID id) {
        try {
            var product = newsRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("News not found")
            );
            newsRepository.deleteById(product.getId());
            return ResultUtil.success(true);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResultDto<ResponseNewsDto> update(UUID id , CreateUpdateNewsDto model){
        try {
            News news = newsRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("News not found")
            );
            if (news != null) {
                var toEntity = newsMapper.toEntity(model);
                var NewsSave = newsRepository.save(toEntity);
                return ResultUtil.success(newsMapper.toDto(NewsSave));
            }else {
                throw new CustomException.BadRequest("News not found");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResultDto<ResponseNewsDto> getById(UUID id){
        try {
            var product = newsRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("News not found")
            );
            return ResultUtil.success(newsMapper.toDto(product));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
