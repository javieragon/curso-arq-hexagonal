package com.example.domain.service;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import com.example.domain.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class OrderService {
	@Inject
	OrderRepository orderRepository;
	@Transactional
	public Order createOrder(Order order) {
		this.orderRepository.persist(order);
		return order;
	}
	@Transactional
	public void addItemToOrder(Long orderId, OrderItem item) {
		final Order order = this.orderRepository.findById(orderId);
		if (order != null) {
			order.addItem(item);
			this.orderRepository.persist(order);
		}
	}

	@Transactional
	public void updateOrderStatus(Long orderId, String status) {
		final Order order = this.orderRepository.findById(orderId);
		if (order != null) {
			order.updateStatus(status);
			this.orderRepository.persist(order);
		}
	}

	public List<Order> getAllOrders() {
		return this.orderRepository.listAll();
	}
	public Order findOrderById(Long id) {
		return this.orderRepository.findById(id);
	}
}