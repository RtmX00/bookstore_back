package com.example.test.service.Ticket;

import com.example.test.dal.Ticket;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.Ticket.CreateUpdateTicketDto;
import com.example.test.dto.Ticket.ResponseTicketDto;
import com.example.test.mapper.TicketMapper;
import com.example.test.repository.TicketRepository;
import com.example.test.repository.UserRepository;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService  {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }
    public ResultDto<ResultPagedDto<List<ResponseTicketDto>>> list(
            int page,
            int pageSize
    ){
        try{
            Sort sort = Sort.by(Sort.Direction.ASC, "createAt");
            Pageable pageable = PageRequest.of(page-1, pageSize ,sort );
            List<ResponseTicketDto> ticket = ticketRepository
                    .findDistinctFromOrderedByCreatedAt(pageable)
                    .stream()
                    .map(ticketMapper::toDto)
                    .toList();
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
    public ResultDto<ResponseTicketDto> create(
            CreateUpdateTicketDto model
    ){
      try {
          if (model.getToId() != null
                  && model.getFromId() != null
                  && model.getContent() != null
          ) {
              Ticket ticket = ticketMapper.toEntity(model);
              var result = ticketRepository.save(ticket);
              return ResultUtil.success(ticketMapper.toDto(result));
          }else {
              throw new CustomException.BadRequest("please enter Filed");
          }
      } catch (CustomException.NewException e) {
          throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
      } catch (Exception e) {
          throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    public  ResultDto<ResultPagedDto<List<ResponseTicketDto>>> getList(
            UUID FormId,
            UUID ToId,
            int page,
            int pageSize
    ){
        try {
            Sort sort = Sort.by(Sort.Direction.ASC, "createAt");
            Pageable pageable = PageRequest.of(page-1, pageSize ,sort );
            List<ResponseTicketDto> Ticket =
                    ticketRepository.
                            findByFromIdAndToId(FormId , ToId , pageable)
                            .stream()
                            .map(ticketMapper::toDto)
                            .toList();
            if (page >= 1 && pageSize>=1) {
                var totalPage = (long) Math.ceil((double) ticketRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page,pageSize,totalPage,Ticket));
            }else {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
