package com.example.tastyindia.ui

import android.os.Bundle
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding
import com.example.tastyindia.utils.Constants

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {
    override val TAG = "RecipeDetails"

    override fun getViewBinding() = FragmentRecipeDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        val recipe = getRecipeDetails(1)
        val recipeName = recipe.recipeName
        log(recipeName)
    }

    override fun addCallbacks() {
        TODO("Not yet implemented")
    }

    private fun getRecipeDetails(id: Int) = listOfRecipe[id]

    companion object {
        fun newInstance(recipeName: String, recipeImageUrl: String) =
            RecipeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.Key.RECIPE_NAME, recipeName)
                    putString(Constants.Key.RECIPE_URL, recipeImageUrl)
                }
            }
    }
}