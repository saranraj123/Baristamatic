package com.order.coffee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.coffee.service.InventoryService;
import com.order.coffee.transport.GenericResponse;
import com.order.coffee.transport.InventoryResponse;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping(value = "/ingredients", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse retrieveInventory(HttpServletRequest request, HttpServletResponse response) {
		InventoryResponse inventoryResponse = inventoryService.retrieveCurrentInventory();
		response.setStatus(HttpStatus.OK.value());
		return inventoryResponse;
	}

	@PutMapping(value = "ingredients/restock", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse restockInventory(HttpServletRequest request, HttpServletResponse response) {
		GenericResponse genericResponse = null;
		genericResponse = inventoryService.restockInventory();
		response.setStatus(HttpStatus.OK.value());
		return genericResponse;
	}

}
