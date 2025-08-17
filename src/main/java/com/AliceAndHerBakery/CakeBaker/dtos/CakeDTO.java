package com.AliceAndHerBakery.CakeBaker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CakeDTO {
    private Long id;

    @NotBlank(message = "Cake name cannot be blank")
    private String name;

    @NotBlank(message = "Cake flavor cannot be blank")
    private String flavor;

    @NotNull(message = "Cake price cannot be null")
    @PositiveOrZero(message = "Cake price must be greater than or equal to zero")
    private Double price;

    private boolean available;
}
