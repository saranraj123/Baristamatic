package com.order.coffee.service.manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.order.coffee.domain.IngredientInventory;

@SpringBootTest
class PriceManagerTest {

	@InjectMocks
	private PriceManager priceManager;

	@Mock
	InventoryManager inventoryManager;

	@Test
	void test_calculateDrinkPrice() {
		String drink = "coffee";
		Map<String, Integer> drinkIngredientMap = new HashMap<>();
		drinkIngredientMap.put("coffee", 3);
		drinkIngredientMap.put("sugar", 0);

		List<IngredientInventory> currentInventoryList = new ArrayList<>();
		IngredientInventory coffeeIngre = new IngredientInventory();
		coffeeIngre.setIngredientId(1);
		coffeeIngre.setIngredientName("coffee");
		coffeeIngre.setUnitCost(0.75);
		coffeeIngre.setAvailability(10);
		currentInventoryList.add(coffeeIngre);

		IngredientInventory sugarIngre = new IngredientInventory();
		sugarIngre.setIngredientId(2);
		sugarIngre.setIngredientName("sugar");
		sugarIngre.setUnitCost(0.25);
		sugarIngre.setAvailability(10);
		currentInventoryList.add(sugarIngre);

		when(inventoryManager.getDrinkIngredientMap(drink)).thenReturn(drinkIngredientMap);
		when(inventoryManager.retrieveInventory()).thenReturn(currentInventoryList);

		Double drinkPrice = priceManager.calculateDrinkPrice(drink);
		System.out.println(drinkPrice);
		assertThat(drinkPrice).isEqualTo(2.25);

	}

}
