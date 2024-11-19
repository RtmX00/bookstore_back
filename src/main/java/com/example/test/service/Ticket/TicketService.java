package com.example.test.service.Ticket;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.Ticket.ResponseTicketDto;
import com.example.test.mapper.ProductMapper;
import com.example.test.mapper.TicketMapper;
import com.example.test.repository.TicketRepository;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService  {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public ResultDto<ResultPagedDto<List<ResponseTicketDto>>> list(
            int page,
            int pageSize
    ){
        try{
            Pageable pageable = PageRequest.of(page-1, pageSize);
            List<ResponseTicketDto> ticket = ticketRepository.findAll(pageable).stream().map(ticketMapper::toDto).toList();
            if (page >= 1 && pageSize>=1) {
                var totalPage = (long) Math.ceil((double) ticketRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page,pageSize,totalPage,ticket));
            }else {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }
        }catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(),e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
