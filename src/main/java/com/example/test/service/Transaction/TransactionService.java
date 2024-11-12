package com.example.test.service.Transaction;

import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.Transaction.ResponseTransactionDto;
import com.example.test.enums.OrderStatus;
import com.example.test.enums.TransactionStatus;
import com.example.test.dal.Orders;
import com.example.test.dal.Transaction;
import com.example.test.mapper.TransacionMapper;
import com.example.test.repository.OrderRepository;
import com.example.test.repository.TransactionRepository;
import com.example.test.repository.UserRepository;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
private  final TransactionRepository transactionRepository;
private  TransacionMapper transacionMapper = Mappers.getMapper(TransacionMapper.class);
private final OrderRepository orderRepository;
private final UserRepository userRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            TransacionMapper transacionMapper,
            OrderRepository orderRepository,
            UserRepository userRepository
    )
    {
        this.transactionRepository = transactionRepository;
        this.transacionMapper = transacionMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Boolean create(UUID orderId , UUID userId){
        try {
            userRepository.findById(userId).orElseThrow(
                    () -> new  CustomException.BadRequest("user does not exist"));
            Orders order = orderRepository.findById(orderId).orElseThrow(
                    () -> new  CustomException.BadRequest("this order does not exist"));
            UUID getOrderUserId = order.getUsers().getId();
            if(getOrderUserId == userId && order.getStatus().equals(OrderStatus.Pending)){
                order.setStatus(OrderStatus.Success);
                Transaction transaction =new Transaction();
                transaction.setStatus(TransactionStatus.Payed);
                transaction.setOrders(order);
                transaction.setTrackingNumber((int)(Math.random() * 100000000 + 1));
                transactionRepository.save(transaction);
                return true;
            }else {
                throw new CustomException.BadRequest("Order not found");
            }
        }catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(),e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    public ResultDto<List<ResponseTransactionDto>> getListByUserId(UUID userId){
//        try {
//            var user = userRepository.findById(userId).orElseThrow(
//                    () -> new  CustomException.BadRequest("user does not exist"));
//           var result = transactionRepository.findByUser(user).stream().map(transacionMapper::toDto).toList();
//           return ResultUtil.success(result);
//        }catch (CustomException.NewException e) {
//            throw new CustomException.NewException(e.getMessage(),e.getStatusCode());
//
//        } catch (Exception e) {
//            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public ResultDto<List<ResponseTransactionDto>> getList(){
        try {
            var result =  transactionRepository.findAll().stream().map(transacionMapper::toDto).toList();
            return ResultUtil.success(result);
        }catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(),e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
