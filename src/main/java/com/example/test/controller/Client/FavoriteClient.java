package com.example.test.controller.Client;


import com.example.test.configuration.ServerHostRequest;
import com.example.test.dto.Favorite.ResponseCuntFavoriteDto;
import com.example.test.dto.Favorite.ResponseFavoriteDto;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.service.Favorite.FavoriteService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("Client/favorite")
public class FavoriteClient {
    private final FavoriteService favoriteService;
    private final UserUtil userUtil;

    public FavoriteClient(
            FavoriteService favoriteService,
            UserUtil userUtil
    ) {
        this.favoriteService = favoriteService;
        this.userUtil = userUtil;
    }

    @GetMapping("/list")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseFavoriteDto>>>> getListByCategory(
            @RequestHeader UUID userId,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int page,
            HttpServletRequest request
    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(favoriteService.getListClient(
                pageSize,
                page,
                userId,
                ServerHostRequest
                .getHost(request)));
    }

    @PostMapping("/create")
    public ResponseEntity<ResultDto<ResponseFavoriteDto>> create(
            @RequestHeader UUID userId,
            @RequestParam UUID productId
    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(favoriteService.create(userId, productId));
    }

    @DeleteMapping
    public ResponseEntity<ResultDto<Boolean>> delete(
            @RequestHeader UUID userId,
            @RequestParam UUID productId
    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(favoriteService.delete(userId, productId));
    }
    @GetMapping("/getFavoriteByproductId")
    public ResponseEntity<ResultDto<ResponseCuntFavoriteDto>> getFavoriteByproductId(
            @RequestHeader UUID userId,
            @RequestParam UUID productId
    ){
        return ResponseEntity.ok(favoriteService.getFavoriteByproductId(productId , userId));
    }
}

