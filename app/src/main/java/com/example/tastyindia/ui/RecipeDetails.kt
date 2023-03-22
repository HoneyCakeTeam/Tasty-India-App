package com.example.tastyindia.ui

import android.util.Log
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding

class RecipeDetails : BaseFragment<FragmentRecipeDetailsBinding>() {
    override val TAG: String
        get() = TODO("Not yet implemented")

    override fun getViewBinding(): FragmentRecipeDetailsBinding {
        TODO("Not yet implemented")
    }

    override fun setUp() {
        val recipe = getRecipeDetails(1)
        val recipeName = recipe.recipeName
        Log.e("TAG", recipeName)
    }

    override fun addCallbacks() {
        TODO("Not yet implemented")
    }

    private fun getRecipeDetails(id: Int) = listOfRecipe[id]

}