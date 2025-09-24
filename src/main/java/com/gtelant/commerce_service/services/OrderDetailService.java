package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.OrderDetail;
import com.gtelant.commerce_service.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetail(){
        return orderDetailRepository.findAll();
    }

    public OrderDetail getOrderDetailById(int id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(id);
        return orderDetailOptional.orElseThrow(() -> new RuntimeException("OrderDetail not found."));
    }

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(int id) {
        orderDetailRepository.deleteById(id);
    }
}
