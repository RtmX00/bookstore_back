package com.example.test.controller.Client;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.category.ResponseCategoryDto;
import com.example.test.service.category.CategoryService;
import com.example.test.utils.ResultPagedDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("client/category")
public class CategoryClient {
    private final CategoryService categoryService;

    public CategoryClient(
            CategoryService categoryService
    )
    {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ResultDto<ResponseCategoryDto>> getById(@Valid UUID categoryId){
        return ResponseEntity.ok(categoryService.getByIdForClient(categoryId));
    }


    @GetMapping("/list")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseCategoryDto>>>> getList(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false ,defaultValue = "20") int pageSize,
            @RequestParam(required = false , defaultValue = "1") int page
    ) {
        return ResponseEntity.ok(categoryService.getList(categoryName ,pageSize,page));
    }

}
