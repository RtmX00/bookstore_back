package com.example.test.controller.Client;

import com.example.test.configuration.ServerHostRequest;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.product.ResponseProductDto;
import com.example.test.enums.FilterLists;
import com.example.test.service.porduct.ProductService;
import com.example.test.utils.ResultPagedDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("client/product")
public class ProductClient {
    private final ProductService productService;

    public ProductClient(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseProductDto>>>> getList(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(name = "FilterLists", required = false) FilterLists filterLists,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(productService.getList(
                        productName,
                        pageSize,
                        page,
                        filterLists,
                        ServerHostRequest
                                .getHost(
                                        request
                                )
                )
        );
    }

    @GetMapping("productId")
    public ResponseEntity<ResultDto<ResponseProductDto>> getById(
            @RequestParam  UUID productId
    ) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResultDto<List<ResponseProductDto>>> getProductsByCategoryId(
            @PathVariable UUID categoryId
    ) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
    }
    @GetMapping("/getProductByCategoryId")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseProductDto>>>> getProductsByCategoryId(
            @RequestParam UUID categoryId,
            @RequestParam(required = false ,defaultValue = "20") int pageSize,
            @RequestParam(required = false , defaultValue = "1") int page
    ){
        return ResponseEntity.ok(productService.getProductByCategoryId(categoryId ,pageSize,page));
    }

}
