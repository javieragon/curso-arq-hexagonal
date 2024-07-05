package com.example.hexagonal.core.domain.service;


import com.example.hexagonal.core.domain.repository.OrderRepository;
import com.example.hexagonal.core.domain.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final static Order order =new Order(null, "Test Order", LocalDateTime.now(), List.of());

    @Test
    void testCreateOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertEquals(order.getDescription(), createdOrder.getDescription());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testGetOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> fetchedOrder = orderService.getOrder(1L);

        assertEquals(order.getDescription(), fetchedOrder.get().getDescription());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<Order> fetchedOrders = orderService.getAllOrders();

        assertEquals(List.of(order.getDescription()), fetchedOrders.stream().map(Order::getDescription).toList());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }
}