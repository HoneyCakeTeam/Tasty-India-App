package com.example.tastyindia.ui


import android.util.Log
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val TAG: String = "HOME"

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {

        Log.i(TAG, "setUppppp: ${getRandomNumbersForRecommendations()}")
        Log.i(TAG, "setUppppp: ${getListOfRecipeForRecommendations().map { it.recipeName }}")
    }

    override fun addCallbacks() {
    }

    fun getRandomNumbersForRecommendations(): List<Int> {
        val listOfRandomNumbers = List(10) { Random.nextInt(0, listOfRecipe.size - 1) }
        return listOfRandomNumbers
    }

    fun getListOfRecipeForRecommendations(): List<Recipe>{
        var randomNumbers = getRandomNumbersForRecommendations()
        var listOfRecipeForRecommendations = mutableListOf<Recipe>()
        for (i in randomNumbers) {
            listOfRecipeForRecommendations.add(listOfRecipe[i])
        }
        return listOfRecipeForRecommendations
    }


}