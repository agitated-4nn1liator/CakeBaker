package com.AliceAndHerBakery.CakeBaker.controllers;

import com.AliceAndHerBakery.CakeBaker.dtos.OrderDTO;
import com.AliceAndHerBakery.CakeBaker.exceptions.ResourceNotFound;
import com.AliceAndHerBakery.CakeBaker.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CakeBakerApi/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //    GET /cakes -> Get all cakes
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    //    GET /cakes/{id} -> Get cake by id
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Optional<OrderDTO> orderDTO = orderService.getOrderById(id);
        return orderDTO
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFound("Order with id: " + id + " not found"));
    }

    //    POST /cakes -> Create Cake
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    //    PUT /cakes/{id} -> Update cake
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDTO));
    }

    //    DELETE /cake/{id} -> Delete cake
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Long id) {
        if(orderService.deleteOrder(id)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
