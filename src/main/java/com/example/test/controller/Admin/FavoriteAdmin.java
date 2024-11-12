package com.example.test.controller.Admin;

import com.example.test.dto.Favorite.ResponseCuntFavoriteAdminDto;
import com.example.test.dto.Favorite.ResponseCuntFavoriteAllAdminDto;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.service.Favorite.FavoriteService;
import com.example.test.utils.UserUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("admin/favorite")
public class FavoriteAdmin {
    private final FavoriteService favoriteService;
    private final UserUtil userUtil;

    public FavoriteAdmin(
            FavoriteService favoriteService,
            UserUtil userUtil
    ) {
        this.favoriteService = favoriteService;
        this.userUtil = userUtil;
    }

    @GetMapping("/List")
    public ResponseEntity<ResultDto<ResponseCuntFavoriteAdminDto>> getList(
            @RequestHeader UUID userId,
            @RequestParam(required = true) UUID productId
    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(favoriteService.getList(productId));
    }

    @GetMapping("/getCountFavoriteByCategory")
    public ResponseEntity<ResultDto<ResponseCuntFavoriteAllAdminDto>> getCountAdminFavoriteByCategory(
            @RequestHeader UUID userId,
            @RequestParam UUID categoryId
    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(favoriteService.getCountAdminFavoriteByCategory(categoryId));
    }

    @GetMapping("/getTopProductFavorite")
    public ResponseEntity<Object> getTopProductFavorite() {
        return ResponseEntity.ok(favoriteService.getTopProductFavorite());
    }
}
