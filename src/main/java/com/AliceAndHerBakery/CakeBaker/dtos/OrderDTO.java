package com.AliceAndHerBakery.CakeBaker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;

    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;

    private LocalDateTime orderDate;

    @Pattern(regexp = "^(PENDING|COMPLETED)$", message = "Status must be either PENDING or COMPLETED")
    private String status;

//    Create a Many-to-one relationship with Cake
}
