package com.AliceAndHerBakery.CakeBaker.repositories;

import com.AliceAndHerBakery.CakeBaker.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
