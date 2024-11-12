package com.example.test.controller.Client;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.order.ResponseOrderDto;
import com.example.test.service.Orders.OrderService;
import com.example.test.utils.UserUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("client/Order")
public class OrderClient {
    private final OrderService orderService;
    private final UserUtil userUtil;

    public OrderClient(
            OrderService orderService,
            UserUtil userUtil
    ) {
        this.orderService = orderService;
        this.userUtil = userUtil;
    }


    @PostMapping
    public ResponseEntity<ResultDto<ResponseOrderDto>> createOrder(
            @RequestHeader @Valid UUID userId,
            @RequestParam @Valid UUID productId,
            @RequestParam @Valid int number
    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(orderService.createOrder(userId, productId, number));
    }

    @GetMapping("/list")
    public ResponseEntity<ResultDto<List<ResponseOrderDto>>> getOrders(
            @RequestHeader @Valid UUID userId
    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/getCurrent")
    public ResponseEntity<ResultDto<ResponseOrderDto>> getCurrent(
            @RequestHeader @Valid UUID userId
    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(orderService.getCurrent(userId));
    }

}
