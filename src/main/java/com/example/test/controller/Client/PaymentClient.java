package com.example.test.controller.Client;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.service.Orders.OrderService;
import com.example.test.utils.UserUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("client/payment")
public class PaymentClient {
    private final OrderService orderService;
    private final UserUtil userUtil;

    public PaymentClient(
            OrderService orderService,
            UserUtil userUtil
    ) {
        this.orderService = orderService;
        this.userUtil = userUtil;
    }

    @PostMapping
    public ResponseEntity<ResultDto<Boolean>> paymentOrder(
            @RequestHeader @Valid UUID userId,
            @RequestParam @Valid UUID orderId
    ) {
        userUtil.isUser(userId);
        return ResponseEntity.ok(orderService.paymentOrder(orderId, userId));
    }
}
