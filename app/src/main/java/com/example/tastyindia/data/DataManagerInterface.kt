package com.example.tastyindia.data

import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.data.domain.HomeCategoriesModel
import com.example.tastyindia.data.domain.KitchenInfo
import com.example.tastyindia.data.domain.Recipe

interface DataManagerInterface {
    fun getKitchenInfoList(): List<KitchenInfo>
    fun getRandomNumbersInListOfRecipe(): List<Int>
    fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe>
    fun getKitchenInfoByName(kitchenName: String): KitchenInfo
    fun getAnonymousKitchen(): KitchenInfo
    fun getAllKitchenRecipes(): List<Recipe>
    fun getAdvicesList(): List<Advice>
    fun getHealthyRecipes(health: List<String>): List<Recipe>
    fun getFastFoodRecipes(): List<Recipe>
    fun getEasyRecipes(): List<Recipe>
    fun getHealthyIngredients(): List<String>
    fun getIngredients(recipe: Recipe): List<String>
    fun getInstructions(recipe: Recipe): List<String>
    fun searchByRecipeOrCuisine(searchWord: String): List<Recipe>
    fun getRecipesByKitchen(kitchenName: String): List<Recipe>
    fun getListOfHomeCategories(): List<HomeCategoriesModel>
    fun getRecipe(id: Int): Recipe
    fun getRecipesByCategory(categoryName: String): ArrayList<Recipe>

    fun getRandomImageInCategory(): String

}