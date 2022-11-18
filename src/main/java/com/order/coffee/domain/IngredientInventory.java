package com.order.coffee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "INGREDIENT_INVENTORY")
public class IngredientInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INGREDIENT_ID")
	private Integer ingredientId;

	@Column(name = "INGREDIENT_NAME")
	private String ingredientName;

	@Column(name = "AVAILABILITY")
	private Integer availability;

	@JsonIgnore
	@Column(name = "UNIT_COST")
	private double unitCost;

	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	@Override
	public String toString() {
		return "IngredientInventory [ingredientId=" + ingredientId + ", ingredientName=" + ingredientName
				+ ", availability=" + availability + ", unitCost=" + unitCost + "]";
	}

}
