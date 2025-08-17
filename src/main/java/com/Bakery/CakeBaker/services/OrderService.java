package com.Bakery.CakeBaker.services;

import com.Bakery.CakeBaker.dtos.OrderDTO;
import com.Bakery.CakeBaker.entities.OrderEntity;
import com.Bakery.CakeBaker.exceptions.ResourceNotFound;
import com.Bakery.CakeBaker.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

//    Check if the order already exists
    public void doesOrderExist(Long id) {
        boolean exists = orderRepository.existsById(id);
        if(!exists) {
            throw new ResourceNotFound("Order with id: " + id + " not found");
        }
    }

    //    GET /orders -> Get all cakes
    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDTO.class))
                .collect(Collectors.toList());
    }

    //    GET /cakes/{id} -> Get cake by id
    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository
                .findById(id)
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDTO.class));
    }

    //    POST /cakes -> Create Cake
    public OrderDTO createOrder(OrderDTO orderDTO) {
        orderDTO.setStatus("PENDING");
        OrderEntity savedOrderEntity = orderRepository.save(modelMapper.map(orderDTO, OrderEntity.class));
        return modelMapper.map(savedOrderEntity, OrderDTO.class);
    }

    //    PUT /cakes/{id} -> Update cake
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        doesOrderExist(id);

        OrderEntity orderEntity = modelMapper.map(orderDTO, OrderEntity.class);
        orderEntity.setId(id);
        OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
        return modelMapper.map(updatedOrderEntity, OrderDTO.class);
    }

    //    DELETE /cake/{id} -> Delete cake
    public boolean deleteOrder(Long id) {
        doesOrderExist(id);
        orderRepository.deleteById(id);
        return true;
    }
}
