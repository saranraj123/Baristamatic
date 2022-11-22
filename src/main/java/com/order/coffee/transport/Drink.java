package com.order.coffee.transport;

public class Drink {

	private Integer drinkId;

	private String drinkName;

	private String drinkCost;

	private boolean availability;

	public Drink(Integer drinkId, String drinkName, String drinkCost, boolean availability) {
		super();
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.drinkCost = drinkCost;
		this.availability = availability;
	}

	public Integer getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(Integer drinkId) {
		this.drinkId = drinkId;
	}

	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	public String getDrinkCost() {
		return drinkCost;
	}

	public void setDrinkCost(String drinkCost) {
		this.drinkCost = drinkCost;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}
