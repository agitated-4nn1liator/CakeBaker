package com.Bakery.CakeBaker.repositories;

import com.Bakery.CakeBaker.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
