package com.example.test.controller.Client;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.user.CreateUpdateUserDto;
import com.example.test.dto.user.LoginUserDto;
import com.example.test.dto.user.ResponseUserDto;
import com.example.test.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("auth")
public class Authentication {
    private final UserService userService;

    public Authentication(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<ResultDto<ResponseUserDto>> login(
            @RequestBody @Valid LoginUserDto model
    )
    {
        return ResponseEntity.ok(userService.login(model));
    }
    @PostMapping("/Register")
    public ResponseEntity<ResultDto<ResponseUserDto>> register(
            @RequestBody CreateUpdateUserDto model
    ){
        return ResponseEntity.ok(userService.registerUser(model));
    }
}
