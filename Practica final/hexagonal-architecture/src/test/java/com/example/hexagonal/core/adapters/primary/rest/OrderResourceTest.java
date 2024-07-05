package com.example.hexagonal.core.adapters.primary.rest;

import com.example.hexagonal.core.domain.entity.Address;
import com.example.hexagonal.core.domain.entity.Customer;
import com.example.hexagonal.core.domain.entity.Order;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderResourceTest {

	private final static Address address = new Address("Test Street", "Test City", "Test Zip");
	private final static Customer customer = new Customer("Test Customer", address);
	private final static Order order = new Order(1L, "Test Order", LocalDateTime.now(), List.of(customer));

	@Test
	@org.junit.jupiter.api.Order(1)
	void testCreateOrder() {

		given().contentType(MediaType.APPLICATION_JSON).body(order).when().post("/orders").then().statusCode(201)
				.body("id", is(order.getId().intValue()));
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	void testGetOrder404() {

		given().when().get("/orders/{id}", "2").then().statusCode(404);
	}

	@Test
	@org.junit.jupiter.api.Order(3)
	void testGetAllOrders() {

		given().when().get("/orders").then().statusCode(200).body("description", is(List.of("Test Order")));
	}
	@Test
	@org.junit.jupiter.api.Order(4)
	void testGetOrder() {

		given().when().get("/orders/{id}", 1).then().statusCode(200).body("description", is("Test Order")).and()
				.body("customers[0].name", is("Test Customer")).and()
				.body("customers[0].address.street", is("Test Street"));
	}

	@Test
	@org.junit.jupiter.api.Order(5)
	void testDeleteOrder() {
		given().pathParam("id", 1L).when().delete("/orders/{id}").then().statusCode(204);
	}
}