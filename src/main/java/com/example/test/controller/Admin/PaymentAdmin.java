package com.example.test.controller.Admin;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.Transaction.ResponseTransactionDto;
import com.example.test.service.Orders.OrderService;
import com.example.test.service.Transaction.TransactionService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("Admin/payment")
public class PaymentAdmin {
    private final UserUtil userUtil;
    private final TransactionService transactionService;

    public PaymentAdmin( UserUtil userUtil, TransactionService transactionService) {
        this.userUtil = userUtil;
        this.transactionService = transactionService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseTransactionDto>>>> getAllTransactions(
            @RequestHeader UUID userId,
            @RequestParam(required = false ,defaultValue = "20") int pageSize,
            @RequestParam(required = false , defaultValue = "1") int page
    ) {
        userUtil.isAdmin(userId);
        return ResponseEntity.ok(transactionService.getList(pageSize,page));

    }
}
