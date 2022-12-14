package com.order.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.coffee.domain.IngredientCost;

@Repository
public interface IngredientCostRepository extends JpaRepository<IngredientCost, Integer> {

}
