package com.menu_project.menuservice.repository;

import com.menu_project.menuservice.entity.menu_order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByIdIn(List<Long> orderIdList);
 }
