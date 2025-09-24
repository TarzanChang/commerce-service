package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
