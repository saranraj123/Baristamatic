package com.order.coffee.domain;

public class Ingredient {

	private Integer coffee;

	private Integer decaf_coffee;

	private Integer sugar;

	private Integer cream;

	private Integer steamed_milk;

	private Integer foamed_milk;

	private Integer espresso;

	private Integer cocoa;

	private Integer whipped_cream;

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
		return "Ingredient [coffee=" + coffee + ", decaf_coffee=" + decaf_coffee + ", sugar=" + sugar + ", cream="
				+ cream + ", steamed_milk=" + steamed_milk + ", foamed_milk=" + foamed_milk + ", espresso=" + espresso
				+ ", cocoa=" + cocoa + ", whipped_cream=" + whipped_cream + "]";
	}

}
