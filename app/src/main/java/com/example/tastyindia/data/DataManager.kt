package com.example.tastyindia.data

import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import kotlin.random.Random


class DataManager(dataSource: CsvDataSource) : DataManagerInterface {

    private val listOfRecipe = dataSource.getAllRecipes()


   //region home screen
    override fun getRandomNumbersForRecommendations(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(0, listOfRecipe.size - 1)
        }
        return listOfRandomNumbers
    }

    override fun getRandomNumbersForRecipesOfTheWeek(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(listOfRecipe.size - 1)
        }
        return listOfRandomNumbers
    }

    override fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe> =
        randomNumbers.map {
            listOfRecipe[it]
        }

    //endregion
    //region category screen
    override fun filterFastFoodRecipes(): List<Recipe> {
        return listOfRecipe.sortedBy {
            it.totalTimeInMins
        }
    }

    override fun filterEasyRecipes(): List<Recipe> {
        return listOfRecipe.sortedBy {
            it.ingredientsCount
        }
    }

    override fun excludeUnHealthyRecipes(recipe: Recipe, health: List<String>): Boolean {
        return health.any {
            recipe.ingredients.lowercase().contains(it.lowercase())
        }
    }
    //endregion
}