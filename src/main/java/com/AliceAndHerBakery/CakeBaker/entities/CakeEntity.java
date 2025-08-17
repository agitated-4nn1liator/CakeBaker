package com.AliceAndHerBakery.CakeBaker.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cakes")
public class CakeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String flavor;

    private Double price;

    private boolean available;
}
