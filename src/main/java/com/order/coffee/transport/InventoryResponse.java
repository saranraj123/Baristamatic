package com.order.coffee.transport;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.order.coffee.domain.IngredientInventory;

@JsonPropertyOrder("message")
public class InventoryResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<IngredientInventory> ingredientInventory;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;

	public List<IngredientInventory> getIngredientInventory() {
		return ingredientInventory;
	}

	public void setIngredientInventory(List<IngredientInventory> ingredientInventory) {
		this.ingredientInventory = ingredientInventory;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
