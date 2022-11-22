package com.order.coffee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DRINK_INGREDIENT")
public class DrinkIngredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRINK_ID")
	private Integer drinkId;

	@Column(name = "DRINK_NAME")
	private String drinkName;

	@Column(name = "COFFEE")
	private Integer coffee;

	@Column(name = "DECAF")
	private Integer decaf_coffee;

	@Column(name = "SUGAR")
	private Integer sugar;

	@Column(name = "CREAM")
	private Integer cream;

	@Column(name = "STEAMED_MILK")
	private Integer steamed_milk;

	@Column(name = "FOAMED_MILK")
	private Integer foamed_milk;

	@Column(name = "ESPRESSO")
	private Integer espresso;

	@Column(name = "COCOA")
	private Integer cocoa;

	@Column(name = "WHIPPED_CREAM")
	private Integer whipped_cream;
	
	
	public DrinkIngredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DrinkIngredient(Integer drinkId, String drinkName, Integer coffee, Integer decaf_coffee, Integer sugar,
			Integer cream, Integer steamed_milk, Integer foamed_milk, Integer espresso, Integer cocoa,
			Integer whipped_cream) {
		super();
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.coffee = coffee;
		this.decaf_coffee = decaf_coffee;
		this.sugar = sugar;
		this.cream = cream;
		this.steamed_milk = steamed_milk;
		this.foamed_milk = foamed_milk;
		this.espresso = espresso;
		this.cocoa = cocoa;
		this.whipped_cream = whipped_cream;
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

	public Integer getCoffee() {
		return coffee;
	}

	public void setCoffee(Integer coffee) {
		this.coffee = coffee;
	}

	public Integer getDecaf_coffee() {
		return decaf_coffee;
	}

	public void setDecaf_coffee(Integer decaf_coffee) {
		this.decaf_coffee = decaf_coffee;
	}

	public Integer getSugar() {
		return sugar;
	}

	public void setSugar(Integer sugar) {
		this.sugar = sugar;
	}

	public Integer getCream() {
		return cream;
	}

	public void setCream(Integer cream) {
		this.cream = cream;
	}

	public Integer getSteamed_milk() {
		return steamed_milk;
	}

	public void setSteamed_milk(Integer steamed_milk) {
		this.steamed_milk = steamed_milk;
	}

	public Integer getFoamed_milk() {
		return foamed_milk;
	}

	public void setFoamed_milk(Integer foamed_milk) {
		this.foamed_milk = foamed_milk;
	}

	public Integer getEspresso() {
		return espresso;
	}

	public void setEspresso(Integer espresso) {
		this.espresso = espresso;
	}

	public Integer getCocoa() {
		return cocoa;
	}

	public void setCocoa(Integer cocoa) {
		this.cocoa = cocoa;
	}

	public Integer getWhipped_cream() {
		return whipped_cream;
	}

	public void setWhipped_cream(Integer whipped_cream) {
		this.whipped_cream = whipped_cream;
	}

	@Override
	public String toString() {
		return "DrinkIngredient [drinkId=" + drinkId + ", drinkName=" + drinkName + ", coffee=" + coffee
				+ ", decaf_coffee=" + decaf_coffee + ", sugar=" + sugar + ", cream=" + cream + ", steamed_milk="
				+ steamed_milk + ", foamed_milk=" + foamed_milk + ", espresso=" + espresso + ", cocoa=" + cocoa
				+ ", whipped_cream=" + whipped_cream + "]";
	}

}
