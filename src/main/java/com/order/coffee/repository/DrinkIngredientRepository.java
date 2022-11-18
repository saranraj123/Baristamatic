package com.order.coffee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.order.coffee.domain.DrinkIngredient;

@Repository
public interface DrinkIngredientRepository extends JpaRepository<DrinkIngredient, Integer> {

	public Optional<DrinkIngredient> findByDrinkName(String drink);

}
