package com.order.coffee.transport;

import java.util.List;

public class Menu extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Drink> drinks;

	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}

}
