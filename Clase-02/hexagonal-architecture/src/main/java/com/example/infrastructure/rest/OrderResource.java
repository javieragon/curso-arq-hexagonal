
package com.example.infrastructure.rest;
import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import com.example.domain.service.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
	@Inject
	OrderService orderService;
	@POST
	public Response createOrder(Order order) {
		this.orderService.createOrder(order);
		return Response.status(Response.Status.CREATED).entity(order).build();
	}

	@POST
	@Path("/{orderId}/items")
	public Response addItemToOrder(@PathParam("orderId") Long orderId, OrderItem item) {
		this.orderService.addItemToOrder(orderId, item);
		return Response.ok().build();
	}
	@PUT
	@Path("/{orderId}/status")
	public Response updateOrderStatus(@PathParam("orderId") Long orderId, String status) {
		this.orderService.updateOrderStatus(orderId, status);
		return Response.ok().build();
	}

	@GET

	public List<Order> getAllOrders() {
		return this.orderService.getAllOrders();
	}
	@GET

	@Path("/{orderId}")
	public Order getOrderById(@PathParam("orderId") Long orderId) {
		return this.orderService.findOrderById(orderId);
	}
}
