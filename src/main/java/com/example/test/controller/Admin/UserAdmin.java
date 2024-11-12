package com.example.test.controller.Admin;

import com.example.test.configuration.ServerHostRequest;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.user.CreateUpdateUserDto;
import com.example.test.dto.user.ResponseUserDto;
import com.example.test.service.user.UserService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("admin/user")
public class UserAdmin {
    private final UserService userService;
    private final UserUtil userUtil;

    public UserAdmin(UserService userService, UserUtil userUtil) {
        this.userService = userService;
        this.userUtil = userUtil;
    }

    @GetMapping("/getById")
    public ResponseEntity<ResultDto<ResponseUserDto>> getUserById(
            @RequestHeader UUID ValidatingId,
            @RequestParam UUID UserId
    ) {
        userUtil.isAdmin(ValidatingId);
        return ResponseEntity.ok(userService.findById(UserId));
    }

    @GetMapping("/list")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseUserDto>>>> getListUser(
            @RequestHeader UUID userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false ,defaultValue = "20") int pageSize,
            @RequestParam(required = false , defaultValue = "1") int page,
            HttpServletRequest request
    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(userService.getList(
                userName,
                pageSize,
                page,
                ServerHostRequest
                        .getHost(request)));
    }


    @PostMapping("/create")
    public ResponseEntity<ResultDto<ResponseUserDto>> createUser(
            @RequestHeader UUID userId,
            @RequestBody CreateUpdateUserDto model
    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(userService.create(model));
    }
    @PutMapping("/update")
    public ResponseEntity<ResultDto<ResponseUserDto>> updateUser(
            @RequestHeader UUID validationId,
            @RequestParam UUID userId,
            @RequestBody CreateUpdateUserDto model
    ){
        userUtil.isAdmin(validationId);
        return ResponseEntity.ok(userService.updateProfile(userId,model));
    }
}
