package com.order.coffee.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.order.coffee.domain.IngredientInventory;
import com.order.coffee.service.InventoryService;
import com.order.coffee.transport.InventoryResponse;
import com.order.coffee.util.Constants;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class InventoryControllerTest {

	@InjectMocks
	private InventoryController inventoryController;

	@Mock
	private InventoryService inventoryService;

	@Test
	void test_retrieveInventory() {
		HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse httpResponse = Mockito.mock(HttpServletResponse.class);

		IngredientInventory ingredient = new IngredientInventory();
		ingredient.setIngredientId(1);
		ingredient.setIngredientName("coffee");
		ingredient.setAvailability(10);
		ingredient.setUnitCost(0.75);

		IngredientInventory anotherIngredient = new IngredientInventory();
		anotherIngredient.setIngredientId(2);
		anotherIngredient.setIngredientName("sugar");
		anotherIngredient.setAvailability(5);
		anotherIngredient.setUnitCost(0.25);

		List<IngredientInventory> ingrInveList = new ArrayList<>();
		ingrInveList.add(ingredient);
		ingrInveList.add(anotherIngredient);
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setIngredientInventory(ingrInveList);
		inventoryResponse.setMessage(Constants.CURRENT_INVENTORY);
		inventoryResponse.setHttpStatus(HttpStatus.OK);

		when(inventoryService.retrieveCurrentInventory()).thenReturn(inventoryResponse);

		InventoryResponse response = inventoryController.retrieveInventory(httpRequest, httpResponse);
		System.out.println(response);
		assertThat(response.getHttpStatus()).isEqualTo(HttpStatus.OK);
		assertThat(response.getIngredientInventory().size()).isEqualTo(2);
		assertThat(response.getIngredientInventory().get(1).getIngredientName()).isEqualTo("sugar");
	}

	@Test
	void test_restockInventorySuccess() {
		HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse httpResponse = Mockito.mock(HttpServletResponse.class);

		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setMessage(Constants.INVENTORY_RESTOCKED);
		inventoryResponse.setHttpStatus(HttpStatus.OK);
		when(inventoryService.restockInventory()).thenReturn(inventoryResponse);

		InventoryResponse response = inventoryController.restockInventory(httpRequest, httpResponse);
		System.out.println(response);
		assertThat(response.getHttpStatus()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void test_restockInventoryFailure() {
		HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse httpResponse = Mockito.mock(HttpServletResponse.class);

		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setMessage(Constants.INVENTORY_NOT_RESTOCKED);
		inventoryResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
		when(inventoryService.restockInventory()).thenReturn(inventoryResponse);

		InventoryResponse response = inventoryController.restockInventory(httpRequest, httpResponse);
		System.out.println(response);
		assertThat(response.getHttpStatus()).isEqualTo(HttpStatus.EXPECTATION_FAILED);
	}

}
