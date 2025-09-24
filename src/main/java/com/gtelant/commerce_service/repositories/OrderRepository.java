package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Orders,Integer>, JpaSpecificationExecutor<Orders> {
}
