package com.example.test.controller.Admin;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.order.ResponseOrderDto;
import com.example.test.service.Orders.OrderService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("admin/order")
public class OrderAdmin {
    private final OrderService orderService;
    private final UserUtil userUtil;

    public OrderAdmin(
            OrderService orderService,
            UserUtil userUtil
    ) {
        this.orderService = orderService;
        this.userUtil = userUtil;
    }

    @GetMapping("/list")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseOrderDto>>>> getAllOrders(
            @RequestHeader UUID userid,
            @RequestParam(required = false) String orderName,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int page
    ) {
        userUtil.isAdmin(userid);
        return ResponseEntity.ok(orderService.getLatestOrder(orderName, pageSize, page));
    }
    @GetMapping("/getOrdersByUserId")
    public ResponseEntity<ResultDto<List<ResponseOrderDto>>> getOrdersByUserId(
            @RequestHeader UUID validationId,
            @RequestParam @Valid UUID userId

    ) {
        userUtil.isAdmin(validationId);
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

}
