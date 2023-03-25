package com.example.tastyindia.data

import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.data.domain.KitchenInfo
import com.example.tastyindia.data.domain.Recipe

interface DataManagerInterface {
    fun getRandomNumbersForRecommendations(): List<Int>
    fun getRandomNumbersForRecipesOfTheWeek(): List<Int>
    fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe>
    fun getKitchenInfo(kitchenName: String): MutableList<KitchenInfo>
    fun getAllKitchen(): List<Recipe>
    fun getAdvicesList(): List<Advice>
    fun getHealthyRecipes(health: List<String>): List<Recipe>
    fun getFastFoodRecipes(): List<Recipe>
    fun getEasyRecipes(): List<Recipe>
    fun getHealthyIngredients(): List<String>
}