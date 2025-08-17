package com.AliceAndHerBakery.CakeBaker.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerName;

    @UpdateTimestamp
    private LocalDateTime orderDate;

    private String status;

//    Create a Many-to-one relationship with Cake
}
