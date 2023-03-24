package com.example.tastyindia.ui


import android.util.Log
import android.widget.Toast
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeRecommendationsListener {

    override val TAG: String = "HomeFragment"

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {

        val randomNumbersForRecommendations = getRandomNumbersForRecipesOfTheWeek()
        val listOfRecommendationRecipes = getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)
        Log.i(TAG, "setUppppp: ${getRandomNumbersForRecommendations()}")
        Log.i(TAG, "setUppppp: ${listOfRecommendationRecipes.map { it.recipeName }}")

        val adapter = HomeRecommendationAdapter(listOfRecommendationRecipes , this)
        binding?.rvHomeRecommendation?.adapter = adapter


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

    override fun onClickItem(id: Int) {
        Toast.makeText(requireContext(),"$id",Toast.LENGTH_SHORT).show()
    }


}