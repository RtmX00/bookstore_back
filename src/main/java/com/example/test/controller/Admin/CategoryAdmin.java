package com.example.test.controller.Admin;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.category.CreateUpdateCategoryDto;
import com.example.test.dto.category.ResponseCategoryDto;
import com.example.test.dto.product.CreateUpdateProductDto;
import com.example.test.dto.product.ResponseProductDto;
import com.example.test.service.category.CategoryService;
import com.example.test.service.porduct.ProductService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import jakarta.validation.Valid;
import org.springdoc.core.converters.models.Pageable;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("admin/category")
public class CategoryAdmin {
    private final CategoryService categoryService;
    private final UserUtil userUtil;
    private final ProductService productService;

    public CategoryAdmin(
            CategoryService categoryService,
            UserUtil userUtil,
            ProductService productService) {
        this.categoryService = categoryService;
        this.userUtil = userUtil;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResultDto<ResponseCategoryDto>> create(
            @RequestBody CreateUpdateCategoryDto model ,
            @RequestHeader UUID userId
    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(categoryService.create(model));
    }

    @DeleteMapping
    public void deleteById(
            @Valid UUID categoryId ,
            @RequestHeader UUID userId
    ){
        userUtil.isAdmin(userId);
            categoryService.deleteById(categoryId);
    }

    @GetMapping
    public ResponseEntity<ResultDto<ResponseCategoryDto>> getById(
            @RequestHeader UUID userid,
            @Valid UUID categoryId
    ){
        userUtil.isAdmin(userid);
        return ResponseEntity.ok(categoryService.getByIdForAdmin(categoryId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResultDto<ResponseCategoryDto>> update(
            @PathVariable @Valid UUID categoryId,
            @RequestBody @Valid CreateUpdateCategoryDto model ,
            @RequestHeader UUID userId){
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(categoryService.update(categoryId,model));
    }

    @GetMapping("/list")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseCategoryDto>>>> getList(
            @RequestHeader UUID userId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false ,defaultValue = "20") int pageSize,
            @RequestParam(required = false , defaultValue = "1") int page
            ) {
        userUtil.isAdmin(userId);
    return ResponseEntity.ok(categoryService.getList(categoryName, pageSize,page));
    }


}
