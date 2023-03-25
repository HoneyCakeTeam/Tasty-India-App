package com.example.tastyindia.data

import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.data.domain.KitchenInfo
import com.example.tastyindia.data.domain.Recipe

interface DataManagerInterface {
    fun getRandomNumbersForRecommendations(): List<Int>
    fun getRandomNumbersForRecipesOfTheWeek(): List<Int>
    fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe>

    fun filterFastFoodRecipes(): List<Recipe>
    fun filterEasyRecipes(): List<Recipe>
    fun excludeUnHealthyRecipes(recipe: Recipe, health: List<String>): Boolean
    fun getKitchenInfo(kitchenName: String): MutableList<KitchenInfo>
    fun getAllKitchen(): List<Recipe>
    fun getAdvicesList(): List<Advice>
}