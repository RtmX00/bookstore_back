package com.example.test.service.category;

import com.example.test.dal.Products;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.category.CreateUpdateCategoryDto;
import com.example.test.dto.category.ResponseCategoryDto;
import com.example.test.mapper.CategoryMapper;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.ProductsRepository;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    private final ProductsRepository productsRepository;


    public CategoryService(
            CategoryRepository categoryRepository,
            ProductsRepository productsRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.productsRepository = productsRepository;
    }

    public ResultDto<ResultPagedDto<List<ResponseCategoryDto>>> getList(
            String categoryName,
            int pageSize,
            int page
            ) {
        try {
            Pageable pageable = PageRequest.of(
                    page-1, pageSize
            ); // Assuming `page` is 1-based
            List<ResponseCategoryDto> category = categoryRepository
                    .findByNameContainingOrAll(categoryName, pageable)
                    .stream()
                    .map(categoryMapper::toDto)
                    .toList();

            if(pageSize>0 && page>0){
                var totalPage = (long) Math.ceil((double) productsRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page,pageSize,totalPage,category));
            }else {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<ResponseCategoryDto> create(CreateUpdateCategoryDto model) {
        try {
            var category = categoryMapper.toEntity(model);
            category.setCondition(true);
            var result = categoryRepository.save(category);
            return ResultUtil.success(categoryMapper.toDto(result));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<Boolean> deleteById(UUID id) {
        try {
            var category = categoryRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("Category not found")
            );
            category.setCondition(false);
            List<Products> product = productsRepository.findByCategoryId(category.getId());
            if (product.size() == 0) {
                categoryRepository.deleteById(id);
            }else {
                throw new CustomException.BadRequest("you have product in Gategory please delete this one");
            }
            return ResultUtil.success(true);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<ResponseCategoryDto> getByIdForClient(UUID id) {
        try {
            var result = categoryRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("Category not found")
            );
            if (result.getCondition() == true) {
                return ResultUtil.success(categoryMapper.toDto(result));
            }else {
                throw new CustomException.BadRequest("category not found");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResultDto<ResponseCategoryDto> getByIdForAdmin(UUID id) {
        try {
            var result = categoryRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("Category not found")
            );
            return ResultUtil.success(categoryMapper.toDto(result));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResponseCategoryDto> update(UUID id, CreateUpdateCategoryDto model) {
        try {
            var category = categoryRepository.findById(id).orElseThrow(
                    () -> new CustomException.NewException("Category not found", HttpStatus.BAD_REQUEST)
            );
            category.setName(model.getName());
            return ResultUtil.success(categoryMapper.toDto(categoryRepository.save(category)));

        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
