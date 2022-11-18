package com.order.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.coffee.domain.IngredientInventory;

@Repository
public interface IngredientInventoryRepository extends JpaRepository<IngredientInventory, Integer> {

	public IngredientInventory findByIngredientName(String ingredientName);

}
