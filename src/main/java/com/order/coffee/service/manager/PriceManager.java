package com.order.coffee.service.manager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.coffee.domain.IngredientInventory;

@Service
public class PriceManager {

	@Autowired
	InventoryManager inventoryManager;

	private static final DecimalFormat df = new DecimalFormat("0.00");

	public double calculateDrinkPrice(String drinkName) {
		// 1. Get the list of ingredients needed for the drink
		Map<String, Integer> drinkIngredientMap = inventoryManager.getDrinkIngredientMap(drinkName);

		// 2. Get current inventory
		Map<String, Double> currentPriceMap = getDrinkPriceMap(drinkName);

		// 3. Iterate through map and calculate price
		Map<String, Double> drinkPriceMap = new HashMap<>();
		drinkPriceMap = getPriceMap(drinkIngredientMap, currentPriceMap, drinkPriceMap);

		Iterator<Double> iterator = drinkPriceMap.values().iterator();
		double totalPrice = 0.0;
		while (iterator.hasNext()) {
			double price = 0.0;
			price = iterator.next();
			totalPrice += price;
		}
		return Double.parseDouble(df.format(totalPrice));
	}

	private Map<String, Double> getDrinkPriceMap(String drinkName) {
		List<IngredientInventory> currentInventory = inventoryManager.retrieveInventory();
		return currentInventory.stream()
				.collect(Collectors.toMap(IngredientInventory::getIngredientName, IngredientInventory::getUnitCost));
	}

	private Map<String, Double> getPriceMap(Map<String, Integer> drinkIngredientMap,
			Map<String, Double> currentPriceMap, Map<String, Double> drinkPriceMap) {

		drinkIngredientMap.entrySet().stream().forEach(entry -> {
			drinkPriceMap.put(entry.getKey(), currentPriceMap.get(entry.getKey()) * entry.getValue());
		});
		return drinkPriceMap;
	}
}
