package com.order.coffee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.order.coffee.domain.DrinkIngredient;
import com.order.coffee.repository.DrinkIngredientRepository;
import com.order.coffee.service.manager.InventoryManager;
import com.order.coffee.service.manager.PriceManager;
import com.order.coffee.transport.Drink;
import com.order.coffee.transport.Menu;
import com.order.coffee.transport.OrderResponse;
import com.order.coffee.util.Constants;

@Service
public class OrderService {

	@Autowired
	InventoryService inventoryService;

	@Autowired
	InventoryManager inventoryManager;

	@Autowired
	PriceManager priceManager;

	@Autowired
	DrinkIngredientRepository drinkIngredientRepository;

	public OrderResponse processOrder(String drink) {
		return inventoryService.checkInventory(drink);
	}

	public Menu displayMenu() {
		List<DrinkIngredient> drinkIngredient = drinkIngredientRepository.findAll();
		Menu menu = new Menu();
		List<Drink> drinks = new ArrayList<>();
		drinkIngredient.stream().forEach(ingredient -> drinks.add(new Drink(ingredient.getDrinkId(),
				ingredient.getDrinkName(),
				"$" + String.valueOf(priceManager.calculateDrinkPrice(ingredient.getDrinkName())),
				inventoryManager.isInventoryAvailable(ingredient.getDrinkName(), Constants.REQUEST_SOURCE_MENU))));
		menu.setDrinks(drinks);
		menu.setHttpStatus(HttpStatus.OK);
		return menu;

	}

}
