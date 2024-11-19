package com.example.test.controller.Admin;

import com.example.test.configuration.ServerHostRequest;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.product.CreateUpdateProductDto;
import com.example.test.dto.product.ResponseProductDto;
import com.example.test.enums.FilterLists;
import com.example.test.service.porduct.ProductService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("admin/product")
public class ProductAdmin {
    private final ProductService productService;
    private final UserUtil userUtil;


    public ProductAdmin(
            ProductService productService,
            UserUtil userUtil
    ) {
        this.productService = productService;
        this.userUtil = userUtil;
    }


    @DeleteMapping
    public void deleteById(
            @RequestParam @Valid UUID productId,
            @RequestHeader UUID userId
    ) {
        userUtil.isAdmin(userId);
        productService.deleteById(productId);
    }

    @GetMapping
    public ResponseEntity<ResultDto<ResponseProductDto>> getById(
            @RequestParam UUID ProductId,
            @RequestHeader UUID userId
    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(productService.getById(ProductId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ResultDto<ResponseProductDto>> update(
            @PathVariable UUID productId,
            @RequestBody @Valid CreateUpdateProductDto model,
            @RequestHeader UUID userId
    ) {
        userUtil.checkAdmin(userId);
        return ResponseEntity.ok(productService.update(productId, model));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResultDto<List<ResponseProductDto>>> getProductsByCategoryId(
            @RequestHeader UUID userId,
            @PathVariable UUID categoryId
    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
    }

    @GetMapping("/list")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseProductDto>>>> getList(
            @RequestHeader UUID userId,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false ,defaultValue = "20") int pageSize,
            @RequestParam(required = false , defaultValue = "1") int page,
            @RequestParam(name = "FilterLists"  , required = false) FilterLists filterLists,
            HttpServletRequest request

    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(productService.getList(
                productName,
                pageSize,
                page,
                filterLists,
                ServerHostRequest.getHost(request)
        )
        );
    }
    @PostMapping("/create")
    public ResponseEntity<ResultDto<ResponseProductDto>> create(
            @RequestHeader UUID userId,
            @RequestBody CreateUpdateProductDto model
    ){
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(productService.create(model));
    }




}
