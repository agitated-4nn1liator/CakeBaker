package com.AliceAndHerBakery.CakeBaker.repositories;

import com.AliceAndHerBakery.CakeBaker.entities.CakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
}
