package com.example.test.controller.Client;

import com.example.test.dto.News.ResponseNewsDto;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.service.News.NewsService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("client/News")
public class NewsClient {
    private final NewsService newsService;


    public NewsClient(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseNewsDto>>>> list(
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int page
    ){
        return ResponseEntity.ok(newsService.list(pageSize , page));
    }
}
