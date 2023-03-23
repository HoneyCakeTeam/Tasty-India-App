package com.example.tastyindia.ui


import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val tag: String = "HomeFragment"

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {

        val randomNumbersForRecommendations = getRandomNumbersForRecipesOfTheWeek()
        log("setUppppp: ${getRandomNumbersForRecommendations()}")
        log("setUppppp: ${
            getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)
                .map { it.recipeName }
        }")

        val randomNumbers = getRandomNumbersForRecipesOfTheWeek()
        val recipesOfTheWeek = getListOfRecipeUsingRandomNumbers(randomNumbers)
        log("setUpppp: ${randomNumbers}")
        log("setUpppp: ${recipesOfTheWeek.map { it.recipeName }}")
    }

    override fun addCallbacks() {
    }

    private fun getRandomNumbersForRecommendations(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(0, listOfRecipe.size - 1)
        }
        return listOfRandomNumbers
    }

    private fun getRandomNumbersForRecipesOfTheWeek(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(listOfRecipe.size - 1)
        }
        return listOfRandomNumbers
    }

    private fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe> =
        randomNumbers.map {
            listOfRecipe[it]
        }


}