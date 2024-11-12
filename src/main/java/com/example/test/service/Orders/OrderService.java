package com.example.test.service.Orders;


import com.example.test.dal.Orders;
import com.example.test.dal.Products;
import com.example.test.dal.Users;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.category.ResponseCategoryDto;
import com.example.test.dto.order.ResponseOrderDto;
import com.example.test.enums.OrderStatus;
import com.example.test.mapper.OrderMapper;
import com.example.test.repository.OrderRepository;
import com.example.test.repository.ProductsRepository;
import com.example.test.repository.UserRepository;
import com.example.test.service.Transaction.TransactionService;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService  {
    private final ProductsRepository productsRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TransactionService transactionService;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    public OrderService(
            ProductsRepository productsRepository,
            OrderRepository orderRepository,
            UserRepository userRepository,
            TransactionService transactionService
    ) {
        this.productsRepository = productsRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.transactionService = transactionService;
    }

    public ResultDto<ResultPagedDto<List<ResponseOrderDto>>> getLatestOrder(
            String orderName,
            int pageSize,
            int page
    ) {
        try {
            Pageable pageable = PageRequest.of(page-1, pageSize); // Assuming `page` is 1-based
            List<ResponseOrderDto> category = orderRepository
                    .findByNameContainingOrAll(orderName, pageable)
                    .stream()
                    .map(orderMapper::toDto)
                    .toList();
            if(page >= 1 && pageSize>=1){
                var totalPage = (long) Math.ceil((double) productsRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page, pageSize, totalPage, category));
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

    public ResultDto<List<ResponseOrderDto>> getOrdersByUserId(UUID userId) {
        try {
            var user = userRepository.findById(userId).orElseThrow(
                    () -> new CustomException.BadRequest("user not found"));
            var order = orderRepository.findByUsers(user).stream().map(orderMapper::toDto).toList();
            return ResultUtil.success(order);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResponseOrderDto> getCurrent(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException.BadRequest("user not found"));
        try {
            var currentOrder = orderRepository.findFirstByUsersAndStatus(user, OrderStatus.Pending);
            return ResultUtil.success(orderMapper.toDto(currentOrder));

        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResponseOrderDto> createOrder(UUID userId, UUID productId, int number) {
        try {
            Users user = userRepository.findById(userId).orElseThrow(
                    () -> new CustomException.BadRequest("User not found"));
            Products products = productsRepository.findById(productId).orElseThrow(
                    () -> new CustomException.BadRequest("Product not found"));
            var currentOrder = orderRepository.findFirstByUsersAndStatus(user, OrderStatus.Pending);
            if(currentOrder != null) {
                currentOrder.setStatus(OrderStatus.Cancel);
                orderRepository.save(currentOrder);
            }
            var order = new Orders();
            order.setUsers(user);
            order.setName(products.getName());
            order.setPrice(products.getPrice());
            order.setNumber(number);
            order.setStatus(OrderStatus.Pending);
            order.setPrice(products.getPrice());
            order.setTotalPrice(number * products.getPrice());
            orderRepository.save(order);
            return ResultUtil.success(orderMapper.toDto(order));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResultDto<Boolean> paymentOrder(UUID orderId, UUID userId) {

        try {
            userRepository.findById(userId).orElseThrow(
                    () -> new CustomException.BadRequest("user does not exist"));
            var order = orderRepository.findById(orderId).orElseThrow(
                    () -> new CustomException.BadRequest("this order does not exist"));

            UUID getOrderUserId = order.getUsers().getId();
            if (getOrderUserId == userId && order.getStatus().equals(OrderStatus.Pending)) {
                order.setStatus(OrderStatus.Success);
                orderRepository.save(order);
                transactionService.create(order.getId(), userId);
                return ResultUtil.success(true);
            } else {
                throw new RuntimeException("Order with id " + orderId + " not found");
            }

        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());

        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
