package com.order.coffee.service.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.coffee.domain.DrinkIngredient;
import com.order.coffee.domain.Ingredient;
import com.order.coffee.domain.IngredientInventory;
import com.order.coffee.repository.DrinkIngredientRepository;
import com.order.coffee.repository.IngredientInventoryRepository;
import com.order.coffee.transport.InventoryResponse;
import com.order.coffee.util.Constants;

@Service
public class InventoryManager {

	@Autowired
	IngredientInventoryRepository inventoryRepository;

	@Autowired
	DrinkIngredientRepository drinkIngredientRepository;

	public boolean isInventoryAvailable(String drinkName, String requestSource) {
		// 1. Get the list of ingredients needed for the drink
		Map<String, Integer> drinkIngredientMap = getDrinkIngredientMap(drinkName);

		// 2. Get current inventory
		List<IngredientInventory> currentInventory = retrieveInventory();
		Map<String, Integer> currentInventoryMap = getCurrentInventoryMap(drinkName, currentInventory);

		// 3. Iterate keys and compare values from both map and determine availability
		Map<String, Integer> updatedInventoryMap = new HashMap<>();
		updatedInventoryMap = getUpdatedInventoryMap(drinkIngredientMap, currentInventoryMap, updatedInventoryMap);

		Iterator<Integer> iterator = updatedInventoryMap.values().iterator();
		while (iterator.hasNext()) {
			Integer value = iterator.next();
			if (value < 0) {
				return false;
			}
		}

		if (requestSource.equalsIgnoreCase(Constants.REQUEST_SOURCE_ORDER)) {
			// Update the inventory after the drink is made
			Map<String, Integer> finalInventoryMap = new HashMap<>(updatedInventoryMap);
			currentInventory.stream().forEach(ingr -> {
				ingr.setAvailability(finalInventoryMap.get(ingr.getIngredientName()));
			});
			inventoryRepository.saveAll(currentInventory);
		}
		return true;
	}

	protected Map<String, Integer> getDrinkIngredientMap(String drinkName) {
		DrinkIngredient drinkIngredient = drinkIngredientRepository.findByDrinkName(drinkName).get();
		Ingredient ingredient = convertToIngredient(drinkIngredient);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(ingredient, new TypeReference<Map<String, Integer>>() {
		});
	}

	public List<IngredientInventory> retrieveInventory() {
		return inventoryRepository.findAll();
	}

	private Map<String, Integer> getUpdatedInventoryMap(Map<String, Integer> drinkIngredientMap,
			Map<String, Integer> currentInventoryMap, Map<String, Integer> updatedInventoryMap) {
		drinkIngredientMap.entrySet().stream().forEach(entry -> {
			updatedInventoryMap.put(entry.getKey(), currentInventoryMap.get(entry.getKey()) - entry.getValue());
		});
		return updatedInventoryMap;
	}

	private Map<String, Integer> getCurrentInventoryMap(String drinkName, List<IngredientInventory> currentInventory) {
		return currentInventory.stream().collect(
				Collectors.toMap(IngredientInventory::getIngredientName, IngredientInventory::getAvailability));
	}

	private Ingredient convertToIngredient(DrinkIngredient drinkIngredient) {
		Ingredient ingredient = new Ingredient();
		ingredient.setCocoa(drinkIngredient.getCocoa());
		ingredient.setCoffee(drinkIngredient.getCoffee());
		ingredient.setDecaf_coffee(drinkIngredient.getDecaf_coffee());
		ingredient.setCream(drinkIngredient.getCream());
		ingredient.setEspresso(drinkIngredient.getEspresso());
		ingredient.setFoamed_milk(drinkIngredient.getFoamed_milk());
		ingredient.setSteamed_milk(drinkIngredient.getSteamed_milk());
		ingredient.setSugar(drinkIngredient.getSugar());
		ingredient.setWhipped_cream(drinkIngredient.getWhipped_cream());
		return ingredient;
	}

	public InventoryResponse restockInventory() {
		InventoryResponse inventoryResponse = new InventoryResponse();
		List<IngredientInventory> inventory = retrieveInventory();
		inventory.stream().forEach(ing -> {
			ing.setAvailability(10);
		});

		try {
			inventoryRepository.saveAll(inventory);
			inventoryResponse.setMessage(Constants.INVENTORY_RESTOCKED);
			inventoryResponse.setHttpStatus(HttpStatus.OK);
		} catch (Exception e) {
			inventoryResponse.setMessage(Constants.INVENTORY_NOT_RESTOCKED);
			inventoryResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
		}
		return inventoryResponse;
	}

}
