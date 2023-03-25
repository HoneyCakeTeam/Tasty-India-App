package com.example.tastyindia.data

import androidx.core.content.ContentProviderCompat.requireContext
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.data.source.RecipeDataSource
import com.example.tastyindia.utils.CsvParser
import kotlin.random.Random


class DataManager(dataSource: CsvDataSource) : DataManagerInterface {

    private val listOfRecipe = dataSource.getAllRecipes()



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

}