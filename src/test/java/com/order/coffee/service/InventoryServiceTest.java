package com.order.coffee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.order.coffee.domain.DrinkIngredient;
import com.order.coffee.repository.DrinkIngredientRepository;
import com.order.coffee.repository.IngredientInventoryRepository;
import com.order.coffee.service.manager.InventoryManager;
import com.order.coffee.service.manager.PriceManager;
import com.order.coffee.transport.OrderResponse;

@SpringBootTest
class InventoryServiceTest {

	@InjectMocks
	private InventoryService inventoryService;

	@Mock
	InventoryManager inventoryManager;

	@Mock
	PriceManager priceManager;
	@Mock
	IngredientInventoryRepository inventoryRepository;

	@Mock
	DrinkIngredientRepository drinkIngredientRepository;

	@Test
	void test_inValidDrink() {
		String drink = "White Mocha";
		when(drinkIngredientRepository.findByDrinkName(drink)).thenReturn(Optional.empty());
		OrderResponse orderResponse = inventoryService.checkInventory(drink);
		System.out.println(orderResponse);
		assertThat(orderResponse.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void test_ValidDrink_InventoryAvailable() {
		String drink = "coffee";
		DrinkIngredient drinkIngredient = new DrinkIngredient(1, "coffee", 3, 1, 1, 1, 0, 0, 0, 0, 0);
		when(drinkIngredientRepository.findByDrinkName(drink)).thenReturn(Optional.of(drinkIngredient));
		when(inventoryManager.isInventoryAvailable(drink, "Order")).thenReturn(true);
		when(priceManager.calculateDrinkPrice(drink)).thenReturn(3.35);
		OrderResponse orderResponse = inventoryService.checkInventory(drink);
		System.out.println(orderResponse);
		assertThat(orderResponse.isInventoryAvailable()).isEqualTo(true);
		assertThat(orderResponse.getHttpStatus()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void test_ValidDrink_Inventory_Not_Available() {
		String drink = "coffee";
		DrinkIngredient drinkIngredient = new DrinkIngredient(1, "coffee", 3, 1, 1, 1, 0, 0, 0, 0, 0);
		when(drinkIngredientRepository.findByDrinkName(drink)).thenReturn(Optional.of(drinkIngredient));
		when(inventoryManager.isInventoryAvailable(drink, "Order")).thenReturn(false);
		OrderResponse orderResponse = inventoryService.checkInventory(drink);
		System.out.println(orderResponse);
		assertThat(orderResponse.isInventoryAvailable()).isEqualTo(false);
		assertThat(orderResponse.getHttpStatus()).isEqualTo(HttpStatus.EXPECTATION_FAILED);
	}

}
