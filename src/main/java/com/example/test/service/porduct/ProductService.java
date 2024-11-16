package com.example.test.service.porduct;

import com.example.test.dal.Products;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.product.CreateUpdateProductDto;
import com.example.test.dto.product.ResponseProductDto;
import com.example.test.enums.FilterLists;
import com.example.test.mapper.ProductMapper;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.ProductsRepository;
import com.example.test.utils.FileUtil;
import com.example.test.utils.ImageFolderProperties;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URL;
import java.util.*;

@Service
public class ProductService {
    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    public ProductService(
            ProductsRepository productsRepository,
            CategoryRepository categoryRepository, FileUtil fileUtil
    ) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    public ResultDto<ResultPagedDto<List<ResponseProductDto>>> getList(
            String productName,
            int pageSize,
            int page,
            FilterLists filterLists,
            String host
            ) {
        try {
            Sort sort = Sort.unsorted() ;
            if (filterLists == FilterLists.Cheap){
                sort = Sort.by(Sort.Order.asc("price"));
            }else if(filterLists == FilterLists.Expensive){
                sort = Sort.by(Sort.Order.desc("price"));
            }else if(filterLists == FilterLists.New){
                sort = Sort.by(Sort.Order.desc("createAt"));
            }else if(filterLists == FilterLists.Old){
                sort = Sort.by(Sort.Order.desc("createAt"));
            }
            Pageable pageable = PageRequest.of(page-1, pageSize , sort); // Assuming `page` is 1-based
            List<ResponseProductDto> product = productsRepository
                    .findByNameContainingOrAll(productName, pageable)
                    .stream()
                    .map(productMapper::toDto)
                    .toList();

            product.forEach( item ->
                    item.setImage(host + "/" + fileUtil
                            .getImage(
                                    item
                                            .getId()
                                            .toString()
                                    , ImageFolderProperties.productFolder
                            )
                    )
            );
            if (page >= 1 && pageSize>=1) {
                var totalPage = (long) Math.ceil((double) productsRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page,pageSize,totalPage,product));
            }else {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }
        }catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    public ResultDto<ResponseProductDto> create(CreateUpdateProductDto model ) {
        try {
            var category = categoryRepository.findById(model.getCategoryId()).orElseThrow(
                    () -> new CustomException.BadRequest("Category not found")
            );
            if (model.getPrice() == 0) {
                throw new CustomException.BadRequest("Price must be greater than 0");
            } else {
                Products product = productMapper.toEntity(model);
                product.setCategory(category);
                var result = productsRepository.save(product);
                if (model.getImage() !=null && !model.getImage().equals("String")) {
                    fileUtil.saveImage(model.getImage(),product.getId().toString(), ImageFolderProperties.productFolder);
                }
                return ResultUtil.success(productMapper.toDto(result));

            }

        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<Boolean> deleteById(UUID id) {
        try {
            var product = productsRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("Product not found")
            );
            productsRepository.deleteById(product.getId());
            return ResultUtil.success(true);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<ResponseProductDto> getById(UUID id) {
        try {
            var result = productsRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("Product not found")
            );
            return ResultUtil.success(productMapper.toDto(result));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResponseProductDto> update(UUID id, CreateUpdateProductDto model) {
        try {
            Optional<Products> products = productsRepository.findById(id);
            if (products.isPresent()) {
                Products productsEntity = products.get();
                productsEntity.setName(model.getName());
                productsEntity.setPrice(model.getPrice());
                return ResultUtil.success(productMapper.toDto(productsRepository.save(productsEntity)));
            } else {
                throw new CustomException.BadRequest("Product not found");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<List<ResponseProductDto>> getProductsByCategoryId(UUID categoryId){
        try {
            var category = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new CustomException.BadRequest("Category not found")
            );
            if(category.getCondition().equals(true)){
                return ResultUtil.success(productsRepository
                        .findByCategory(category)
                        .stream()
                        .map(productMapper::toDto)
                        .toList());
            }else {
                throw new CustomException.BadRequest("Category not found");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
public ResultDto<ResultPagedDto<List<ResponseProductDto>>> getProductByCategoryId(
        UUID categoryId,
        int pageSize,
        int page
) {
        try{
            if (page <= 1 && pageSize <= 1) {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }
            var category = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new CustomException.BadRequest("Category not found")
            );
            List<ResponseProductDto> product = productsRepository
                    .findByCategoryId(category.getId())
                    .stream()
                    .map(productMapper::toDto)
                    .toList();

            var totalPage = (long) Math.ceil((double) productsRepository.count() / pageSize);
            return ResultUtil.success(new ResultPagedDto(page, pageSize, totalPage, product));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
public ResultDto<List<ResponseProductDto>> getProductByNameAuthor(
        String host,
        String nameAuthor
){

       try {
           List<ResponseProductDto> productDtos =
                   productsRepository.
                           findProductsByNameAuthor(nameAuthor).
                           stream().map(productMapper::toDto).
                           toList();
           productDtos.forEach( item ->
                   item.setImage(host + "/" + fileUtil
                           .getImage(
                                   item
                                           .getId()
                                           .toString()
                                   , ImageFolderProperties.productFolder
                           )
                   )
           );
           return ResultUtil.success(productDtos);
       } catch (CustomException.NewException e) {
           throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
       } catch (Exception e) {
           throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
}



}
