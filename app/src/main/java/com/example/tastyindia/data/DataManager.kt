package com.example.tastyindia.data

import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import kotlin.random.Random


class DataManager(private val dataSource: CsvDataSource) : DataManagerInterface {

    private val listOfRecipe = dataSource.getAllRecipes()
    override fun getRandomNumbersForRecommendations(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(0, listOfRecipe.size - 1)
        }
        return listOfRandomNumbers
    }

    override fun getRandomNumbersForRecipesOfTheWeek(): List<Int> {
        TODO("Not yet implemented")
    }

    override fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe> {
        TODO("Not yet implemented")
    }
}