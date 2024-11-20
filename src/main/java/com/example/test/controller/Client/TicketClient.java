package com.example.test.controller.Client;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.Ticket.CreateUpdateTicketDto;
import com.example.test.dto.Ticket.ResponseTicketDto;
import com.example.test.service.Ticket.TicketService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.UserUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("Client/Ticket")
public class TicketClient {
    private final TicketService ticketService;
    private final UserUtil userUtil;

    public TicketClient(UserUtil userUtil, TicketService ticketService) {
        this.userUtil = userUtil;
        this.ticketService = ticketService;
    }

    @GetMapping("/getListTicket")
    public ResponseEntity<ResultDto<ResultPagedDto<List<ResponseTicketDto>>>> getListTicket(
            @RequestHeader UUID userid,
            @RequestHeader UUID FromId,
            @RequestHeader UUID ToId,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int page
    ){
        userUtil.isAdmin(userid);
        return ResponseEntity.ok(ticketService.getList(FromId, ToId, page, pageSize));
    }
    @PostMapping("/create")
    public ResponseEntity<ResultDto<ResponseTicketDto>> create(
            @RequestHeader UUID userId,
            @RequestBody CreateUpdateTicketDto model
    ){
        userUtil.isUser(userId);
        return ResponseEntity.ok(ticketService.create(model));
    }
}
