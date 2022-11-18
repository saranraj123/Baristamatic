package com.order.coffee.transport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class OrderResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private double totalPrice;

	@JsonIgnore
	private boolean isInventoryAvailable;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isInventoryAvailable() {
		return isInventoryAvailable;
	}

	public void setInventoryAvailable(boolean isInventoryAvailable) {
		this.isInventoryAvailable = isInventoryAvailable;
	}

}
