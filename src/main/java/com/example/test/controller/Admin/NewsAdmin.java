package com.example.test.controller.Admin;

import com.example.test.dto.News.CreateUpdateNewsDto;
import com.example.test.dto.News.ResponseNewsDto;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.product.CreateUpdateProductDto;
import com.example.test.dto.product.ResponseProductDto;
import com.example.test.service.News.NewsService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("admin/News")
public class NewsAdmin {
    private final NewsService newsService;
    private final UserUtil userUtil;

    public NewsAdmin(NewsService newsService, UserUtil userUtil) {
        this.newsService = newsService;
        this.userUtil = userUtil;
    }
    @PostMapping("/creat")
    public ResponseEntity<ResultDto<ResponseNewsDto>> addNews(
            @RequestBody CreateUpdateNewsDto model,
            @RequestHeader UUID userId
    ){
        userUtil.checkAdmin(userId);
        return ResponseEntity.ok(newsService.create(model));
    }

    @DeleteMapping
    public void deleteById(
            @RequestParam @Valid UUID productId,
            @RequestHeader UUID userId
    ) {
        userUtil.isAdmin(userId);
        newsService.deleteById(productId);
    }

    @PutMapping("/{newsId}")
    public ResponseEntity<ResultDto<ResponseNewsDto>> update(
            @PathVariable UUID newsId,
            @RequestBody @Valid CreateUpdateNewsDto model,
            @RequestHeader UUID userId
    ) {
        userUtil.checkAdmin(userId);
        return ResponseEntity.ok(newsService.update(newsId, model));
    }

    @GetMapping("/list")
    public  ResponseEntity<ResultDto<ResultPagedDto<List<ResponseNewsDto>>>> getList(
            @RequestHeader UUID userId,
            @RequestParam(required = false ,defaultValue = "20") int pageSize,
            @RequestParam(required = false , defaultValue = "1") int page
    ){
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(newsService.list(pageSize,page));
    }
    @GetMapping("/getById")
    public ResponseEntity<ResultDto<ResponseNewsDto>> getById(
            @RequestHeader UUID userId,
            @RequestParam UUID NewsId
    ){
        userUtil.checkAdmin(userId);
        return ResponseEntity.ok(newsService.getById(NewsId));
    }
}
