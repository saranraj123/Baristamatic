package com.order.coffee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.order.coffee.transport.GenericResponse;
import com.order.coffee.transport.InventoryResponse;
import com.order.coffee.transport.OrderResponse;
import com.order.coffee.util.Constants;

@Service
public class InventoryService {

	@Autowired
	IngredientInventoryRepository inventoryRepository;

	@Autowired
	DrinkIngredientRepository drinkIngredientRepository;

	public boolean isInventoryAvailable(String drinkName, String requestSource) {
		// 1. Get the list of ingredients needed for the drink
		Map<String, Integer> drinkIngredientMap = getDrinkIngredientMap(drinkName);
		System.out.println("drinkIngredientMap::" + drinkIngredientMap);

		// 2. Get current inventory
		List<IngredientInventory> currentInventory = retrieveInventory();
		Map<String, Integer> currentInventoryMap = getCurrentInventoryMap(drinkName, currentInventory);
		System.out.println("currentInventoryMap::" + currentInventoryMap);

		// 3. Iterate keys and compare values from both map and determine availability
		Map<String, Integer> updatedInventoryMap = new HashMap<>();
		updatedInventoryMap = getUpdatedInventoryMap(drinkIngredientMap, currentInventoryMap, updatedInventoryMap);
		System.out.println("UpdatedInventoryMap::" + updatedInventoryMap);

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

	private Map<String, Integer> getDrinkIngredientMap(String drinkName) {
		DrinkIngredient drinkIngredient = drinkIngredientRepository.findByDrinkName(drinkName).get();
		Ingredient ingredient = convertToIngredient(drinkIngredient);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(ingredient, new TypeReference<Map<String, Integer>>() {
		});
	}

	private Map<String, Double> getDrinkPriceMap(String drinkName) {
		List<IngredientInventory> currentInventory = retrieveInventory();
		return currentInventory.stream()
				.collect(Collectors.toMap(IngredientInventory::getIngredientName, IngredientInventory::getUnitCost));
	}

	public double calculateDrinkPrice(String drinkName) {
		// 1. Get the list of ingredients needed for the drink
		Map<String, Integer> drinkIngredientMap = getDrinkIngredientMap(drinkName);
		System.out.println("drinkIngredientMap::" + drinkIngredientMap);

		// 2. Get current inventory
		Map<String, Double> currentPriceMap = getDrinkPriceMap(drinkName);
		System.out.println("currentPriceMap::" + currentPriceMap);

		// 3. Iterate keys and compare values from both map and determine availability
		Map<String, Double> drinkPriceMap = new HashMap<>();
		drinkPriceMap = getPriceMap(drinkIngredientMap, currentPriceMap, drinkPriceMap);
		System.out.println("drinkPriceMap::" + drinkPriceMap);

		Iterator<Double> iterator = drinkPriceMap.values().iterator();
		double totalPrice = 0.0;
		while (iterator.hasNext()) {
			double price = 0.0;
			price = iterator.next();
			totalPrice += price;
		}
		System.out.println("price::" + totalPrice);

		return totalPrice;
	}

	private Map<String, Double> getPriceMap(Map<String, Integer> drinkIngredientMap,
			Map<String, Double> currentPriceMap, Map<String, Double> drinkPriceMap) {

		drinkIngredientMap.entrySet().stream().forEach(entry -> {
			drinkPriceMap.put(entry.getKey(), currentPriceMap.get(entry.getKey()) * entry.getValue());
		});
		return drinkPriceMap;
	}

//	public boolean checkInventory(String drinkName) {
//
//		boolean sufficientInventory = true;
//
//		// 1. Get the list of ingredients needed for the drink
//
//		DrinkIngredient drinkIngredient = drinkIngredientRepository.findByDrinkName(drinkName);
//		Ingredient ingredient = convertToIngredient(drinkIngredient);
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Integer> drinkIngredientMap = mapper.convertValue(ingredient,
//				new TypeReference<Map<String, Integer>>() {
//				});
//
//		System.out.println("drinkIngredientMap::" + drinkIngredientMap);
//
//		// 2. Get current inventory
//		List<IngredientInventory> currentInventory = retrieveInventory();
//		Map<String, Integer> currentInventoryMap = currentInventory.stream().collect(
//				Collectors.toMap(IngredientInventory::getIngredientName, IngredientInventory::getAvailability));
//
//		System.out.println("currentInventoryMap::" + currentInventoryMap);
//
//		// 3. Iterate keys and compare values from both map and determine availability
//
//		Map<String, Integer> updatedInventoryMap = new HashMap<>();
//
//		drinkIngredientMap.entrySet().stream().forEach(entry -> {
//			updatedInventoryMap.put(entry.getKey(), currentInventoryMap.get(entry.getKey()) - entry.getValue());
//		});
//
//		System.out.println("UpdatedInventoryMap::" + updatedInventoryMap);
//
//		// Check if updatedInventory has any -ve values to reject the order
//
//		Iterator<Integer> iterator = updatedInventoryMap.values().iterator();
//		while (iterator.hasNext()) {
//			Integer value = iterator.next();
//			System.out.println("value :" + value);
//			if (value < 0) {
//				return false;
//			}
//		}
//
//		// Update the inventory after the drink is made
//
//		currentInventory.stream().forEach(ingr -> {
//			ingr.setAvailability(getUpdatedAvailability(ingr.getIngredientName(), updatedInventoryMap));
//		});
//
//		inventoryRepository.saveAll(currentInventory);
//
////		List<IngredientInventory> updatedInventoryList = new ArrayList<>();
////
////		updatedInventoryMap.entrySet().stream().forEach(entry -> {
////			IngredientInventory ingredientInventory = new IngredientInventory();
////			ingredientInventory.setIngredientName(entry.getKey());
////			ingredientInventory.setAvailability(entry.getValue());
////			updatedInventoryList.add(ingredientInventory);
////		});
////
////		// List<IngredientInventory> inventory = inventoryRepository.findAll();
////
////		System.out.println(updatedInventoryList);
////		inventoryRepository.saveAll(updatedInventoryList);
//
//		return sufficientInventory;
//	}

//	private Integer getUpdatedAvailability(String ingredientName, Map<String, Integer> updatedInventoryMap) {
//		return updatedInventoryMap.get(ingredientName);
//	}

	private Ingredient convertToIngredient(DrinkIngredient drinkIngredient) {
		// TODO Auto-generated method stub

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

	public List<IngredientInventory> retrieveInventory() {
		return inventoryRepository.findAll();
	}

	public InventoryResponse retrieveCurrentInventory() {
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setIngredientInventory(retrieveInventory());
		inventoryResponse.setMessage("Current Inventory");
		return inventoryResponse;
	}

	public GenericResponse restockInventory() {

		InventoryResponse inventoryResponse = new InventoryResponse();
		List<IngredientInventory> inventory = retrieveInventory();
		inventory.stream().forEach(ing -> {
			ing.setAvailability(10);
		});

		try {
			inventoryRepository.saveAll(inventory);
			inventoryResponse.setMessage("Inventory Restocked");
		} catch (Exception e) {
			inventoryResponse.setMessage("Unable to Restock Inventory");
		}
		return inventoryResponse;
	}

	public OrderResponse checkInventory(String drink) {
		OrderResponse orderResponse = new OrderResponse();
		Optional<DrinkIngredient> drinkIngredient = drinkIngredientRepository.findByDrinkName(drink);
		if (!drinkIngredient.isPresent()) {
			orderResponse.setMessage(drink + " is not part of the menu");
			orderResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			return orderResponse;
		}

		orderResponse.setInventoryAvailable(isInventoryAvailable(drink, Constants.REQUEST_SOURCE_ORDER));
		if (orderResponse.isInventoryAvailable()) {
			orderResponse.setTotalPrice(calculateDrinkPrice(drink));
			orderResponse.setMessage("Enjoy your " + drink);
			orderResponse.setHttpStatus(HttpStatus.OK);
		} else {
			orderResponse
					.setMessage("Sorry, " + drink + " is not available at this point due to insufficient Inventory");
			orderResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
		}
		return orderResponse;
	}

}
