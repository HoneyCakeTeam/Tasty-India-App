package com.example.tastyindia.ui

import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding

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

}