package com.example.pharmacy.service.impl;

import com.example.pharmacy.mapper.CustomMapper;
import com.example.pharmacy.service.OrderService;
import communication.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceThriftImpl implements OrderServiceThrift.Iface {
    private final OrderService orderService;
    private final CustomMapper orderMapper;

    @Override
    public OrderThrift completeOrder(long customerThriftId) throws InvalidOperationException {
        try {
            return orderMapper.mapToOrderThrift(orderService.completeOrder(customerThriftId));
        }catch (RuntimeException e) {
            throw new InvalidOperationException(
                    "Can`t complete order with customerID " + customerThriftId
                            + ". Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<OrderThrift> getOrdersHistory(long customerThriftId) throws InvalidOperationException {
        try {
            return orderService.getOrdersHistory(customerThriftId).stream()
                    .map(orderMapper::mapToOrderThrift)
                    .collect(Collectors.toList());
        }catch (RuntimeException e) {
            throw new InvalidOperationException(
                    "Can`t get orders history with customerId " + customerThriftId
                            + ". Exception: " + e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }
}
