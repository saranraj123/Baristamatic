package com.order.coffee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENT_COST")
public class IngredientCost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INGREDIENT_ID")
	private Integer ingredientId;

	@Column(name = "INGREDIENT_NAME")
	private String ingredientName;

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

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	@Override
	public String toString() {
		return "IngredientCost [ingredientId=" + ingredientId + ", ingredientName=" + ingredientName + ", unitCost="
				+ unitCost + "]";
	}
	
	
	

}
