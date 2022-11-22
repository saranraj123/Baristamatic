package com.order.coffee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.order.coffee.domain.DrinkIngredient;
import com.order.coffee.repository.DrinkIngredientRepository;
import com.order.coffee.service.manager.InventoryManager;
import com.order.coffee.service.manager.PriceManager;
import com.order.coffee.transport.InventoryResponse;
import com.order.coffee.transport.OrderResponse;
import com.order.coffee.util.Constants;

@Service
public class InventoryService {

	@Autowired
	DrinkIngredientRepository drinkIngredientRepository;

	@Autowired
	InventoryManager inventoryManager;

	@Autowired
	PriceManager priceManager;

	public OrderResponse checkInventory(String drink) {
		OrderResponse orderResponse = new OrderResponse();
		Optional<DrinkIngredient> drinkIngredient = drinkIngredientRepository.findByDrinkName(drink);
		if (!drinkIngredient.isPresent()) {
			orderResponse.setMessage(drink + Constants.NOT_PART_OF_MENU);
			orderResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			return orderResponse;
		}

		orderResponse
				.setInventoryAvailable(inventoryManager.isInventoryAvailable(drink, Constants.REQUEST_SOURCE_ORDER));
		if (orderResponse.isInventoryAvailable()) {
			orderResponse.setTotalPrice("$" + String.valueOf(priceManager.calculateDrinkPrice(drink)));
			orderResponse.setMessage(Constants.ENJOY_DRINK + drink);
			orderResponse.setHttpStatus(HttpStatus.OK);
		} else {
			orderResponse.setMessage(drink + Constants.INSUFFICIENT_INVENTORY);
			orderResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
		}
		return orderResponse;
	}

	public InventoryResponse retrieveCurrentInventory() {
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setIngredientInventory(inventoryManager.retrieveInventory());
		inventoryResponse.setMessage(Constants.CURRENT_INVENTORY);
		inventoryResponse.setHttpStatus(HttpStatus.OK);
		return inventoryResponse;
	}

	public InventoryResponse restockInventory() {
		InventoryResponse inventoryResponse = inventoryManager.restockInventory();
		return inventoryResponse;
	}

}
