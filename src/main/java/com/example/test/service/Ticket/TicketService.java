package com.example.test.service.Ticket;

import com.example.test.dal.Favorite;
import com.example.test.dal.Products;
import com.example.test.dal.Users;
import com.example.test.dto.Favorite.*;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.Ticket.ResponseTicketDto;
import com.example.test.mapper.FavoriteMapper;
import com.example.test.mapper.OrderMapper;
import com.example.test.mapper.ProductMapper;
import com.example.test.mapper.TicketMapper;
import com.example.test.repository.*;
import com.example.test.utils.FileUtil;
import com.example.test.utils.ImageFolderProperties;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {
   private final TicketRepository ticketRepository;
   private final UserRepository userRepository;
    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }


    public ResultDto<ResultPagedDto<List<ResponseTicketDto>>> getList(
            UUID userId ,
            int pageSize,
            int page
            ) {
        try {
            Users user = userRepository.findById(userId).orElseThrow(
                    () -> new CustomException.DataNotFound("user not found")
            );
            Pageable pageable = PageRequest.of(page-1, pageSize);
            List<ResponseTicketDto> tickets = ticketRepository.findByUsers(user ,pageable)
                    .stream().map(ticketMapper::toDto).toList();
            if(page >= 1 && pageSize>=1){
                var totalPage = (long) Math.ceil((double) ticketRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page, pageSize, totalPage, tickets));
            }
            else {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResultDto<ResultPagedDto<List<ResponseTicketDto>>> getListAdmin(
            int pageSize,
            int page
    ){
        try {
            Pageable pageable = PageRequest.of(page-1, pageSize);
            List<ResponseTicketDto> tickets = ticketRepository
                    .findDistinctUsers(pageable)
                    .stream()
                    .map(ticketMapper::toDto)
                    .toList();
            if(page >= 1 && pageSize>=1){
                var totalPage = (long) Math.ceil((double) ticketRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page, pageSize, totalPage, tickets));
            }
            else {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }
        }catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
public ResultDto<ResponseCuntFavoriteDto> creatClint(UUID productId , UUID userId){
        try{
            return ResultUtil.success(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
    public ResultDto<ResultPagedDto<List<ResponseFavoriteDto>>> getListClient(
            int pageSize,
            int page,
            UUID userId,
            String host
    ) {
        try {
            return ResultUtil.success(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResponseCuntFavoriteAllAdminDto> getCountAdminFavoriteByCategory(
            UUID categoryId
    ) {
        return ResultUtil.success(null);
    }

    public ResultDto<Object> getTopProductFavorite() {
        try {
            return ResultUtil.success(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResultDto<ResponseFavoriteDto> create(UUID userId, UUID productId) {
        try {
            return ResultUtil.success(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<Boolean> delete(UUID userId, UUID productId) {
        try {
            return ResultUtil.success(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

