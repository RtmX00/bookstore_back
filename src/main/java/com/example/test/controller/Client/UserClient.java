package com.example.test.controller.Client;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.user.CreateUpdateUserDto;
import com.example.test.dto.user.ResponseUserDto;
import com.example.test.dto.user.UpdateUserPasswordDto;
import com.example.test.service.user.UserService;
import com.example.test.utils.UserUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("Client")
public class UserClient {
    private final UserService userService;
    private final UserUtil userUtil;

    public UserClient(
            UserService userService,
            UserUtil userUtil
    ) {
        this.userService = userService;
        this.userUtil = userUtil;
    }

    @GetMapping
    public ResponseEntity<ResultDto<ResponseUserDto>> getProfile(
            @RequestHeader @Valid UUID userId

    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PutMapping
    public ResponseEntity<ResultDto<ResponseUserDto>> updateProfile(
            @RequestHeader @Valid UUID userId,
            @RequestBody @Valid UpdateUserPasswordDto model
    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(userService.changePassword(userId,model));
    }
    @PutMapping("/update")
    public ResponseEntity<ResultDto<ResponseUserDto>> updateUser(
            @RequestHeader UUID validationId,
            @RequestParam UUID userId,
            @RequestBody CreateUpdateUserDto model
    ){
        userUtil.isUser(validationId);
        return ResponseEntity.ok(userService.updateProfile(userId,model));
    }
}
