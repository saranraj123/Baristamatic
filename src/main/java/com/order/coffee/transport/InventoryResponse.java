package com.order.coffee.transport;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.order.coffee.domain.IngredientInventory;

@JsonPropertyOrder("message")
public class InventoryResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<IngredientInventory> ingredientInventory;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;

	@JsonIgnore
	private HttpStatus httpStatus;

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

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "InventoryResponse [ingredientInventory=" + ingredientInventory + ", message=" + message
				+ ", httpStatus=" + httpStatus + "]";
	}

}
