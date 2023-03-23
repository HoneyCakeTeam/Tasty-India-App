package com.example.tastyindia.ui

import android.util.Log
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding

class RecipeDetails : BaseFragment<FragmentRecipeDetailsBinding>() {
    override val tag = "RecipeDetails"

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