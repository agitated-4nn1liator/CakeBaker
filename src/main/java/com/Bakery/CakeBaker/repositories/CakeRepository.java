package com.Bakery.CakeBaker.repositories;

import com.Bakery.CakeBaker.entities.CakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
}
