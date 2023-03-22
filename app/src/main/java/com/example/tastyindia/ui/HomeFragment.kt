package com.example.tastyindia.ui


import android.util.Log
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val TAG: String = "HOME"

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {

        val randomNumbersForRecommendations = getRandomNumbersForRecipesOfTheWeek()
        Log.i(TAG, "setUppppp: ${getRandomNumbersForRecommendations()}")
        Log.i(TAG, "setUppppp: ${
            getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)
                .map { it.recipeName }
        }")

        val randomNumbers = getRandomNumbersForRecipesOfTheWeek()
        val recipesOfTheWeek = getListOfRecipeUsingRandomNumbers(randomNumbers)
        Log.i(TAG, "setUpppp: ${randomNumbers}")
        Log.i(TAG, "setUpppp: ${recipesOfTheWeek.map { it.recipeName }}")
    }

    override fun addCallbacks() {
    }

    private fun getRandomNumbersForRecommendations(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(0, getAllRecipes().size - 1)
        }
        return listOfRandomNumbers
    }

    private fun getRandomNumbersForRecipesOfTheWeek(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(getAllRecipes().size - 1)
        }
        return listOfRandomNumbers
    }

    private fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe> =
        randomNumbers.map {
            getAllRecipes()[it]
        }


}