package com.order.coffee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.coffee.service.OrderService;
import com.order.coffee.transport.GenericResponse;
import com.order.coffee.transport.Menu;
import com.order.coffee.transport.OrderResponse;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/drinks/{drink}")
	public GenericResponse orderDrink(@PathVariable String drink, HttpServletRequest request,
			HttpServletResponse response) {

		OrderResponse orderResponse = new OrderResponse();
		orderResponse = orderService.processOrder(drink);
		response.setStatus(orderResponse.getHttpStatus().value());
		return orderResponse;
	}
	
	@GetMapping(value = "/drinks")
	public GenericResponse menu(HttpServletRequest request,
			HttpServletResponse response) {

		Menu menu = orderService.displayMenu();
		response.setStatus(menu.getHttpStatus().value());
		return menu;
	}
	

}
