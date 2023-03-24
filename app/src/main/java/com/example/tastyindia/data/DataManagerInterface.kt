package com.example.tastyindia.data

import com.example.tastyindia.data.domain.Recipe

interface DataManagerInterface {
    fun getRandomNumbersForRecommendations(): List<Int>
    fun getRandomNumbersForRecipesOfTheWeek(): List<Int>
    fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe>
}