package com.order.coffee.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.order.coffee.service.OrderService;
import com.order.coffee.transport.Menu;
import com.order.coffee.transport.OrderResponse;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class OrderControllerTest {

	@InjectMocks
	private OrderController orderController;

	@Mock
	private OrderService orderService;

	HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
	HttpServletResponse httpResponse = Mockito.mock(HttpServletResponse.class);

	@Test
	void test_OrderDrinkSuccess() {

		String drink = "coffee";
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setTotalPrice("$3.2");
		orderResponse.setInventoryAvailable(true);
		orderResponse.setHttpStatus(HttpStatus.OK);
		when(orderService.processOrder(drink)).thenReturn(orderResponse);

		OrderResponse response = orderController.orderDrink(drink, httpRequest, httpResponse);
		System.out.println(response);
		assertThat(response.isInventoryAvailable()).isEqualTo(true);
		assertThat(response.getHttpStatus()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void test_OrderDrinkFailure() {
		String drink = "coffee";
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setTotalPrice("$0.0");
		orderResponse.setInventoryAvailable(false);
		orderResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
		when(orderService.processOrder(drink)).thenReturn(orderResponse);

		OrderResponse response = orderController.orderDrink(drink, httpRequest, httpResponse);
		System.out.println(response);
		assertThat(response.isInventoryAvailable()).isEqualTo(false);
		assertThat(response.getHttpStatus()).isEqualTo(HttpStatus.EXPECTATION_FAILED);
	}

	@Test
	void test_MenuSuccess() {
		Menu menu = new Menu();
		menu.setHttpStatus(HttpStatus.OK);
		when(orderService.displayMenu()).thenReturn(menu);
		Menu response = orderController.menu(httpRequest, httpResponse);
		assertThat(response.getHttpStatus()).isEqualTo(HttpStatus.OK);
	}

}
