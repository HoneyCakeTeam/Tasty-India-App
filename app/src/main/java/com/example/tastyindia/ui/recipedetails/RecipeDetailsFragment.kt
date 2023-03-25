package com.example.tastyindia.ui.recipedetails

import android.os.Build
import android.os.Bundle
import android.view.View
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {
    override val TAG = "RecipeDetails"
    private lateinit var recipe: Recipe

    override fun getViewBinding() = FragmentRecipeDetailsBinding.inflate(layoutInflater)


    override fun setUp() {
        hideBottomNavigation()
        val recipe = getRecipe()
        val recipeName = recipe.recipeName
        log(recipeName)

        val ingredientsList = getIngredients(recipe)
        val instructionsList = getInstructions(recipe)

        val ingredientsAdapter = IngredientsAdapter(ingredientsList)
        val instructionsAdapter = InstructionsAdapter(instructionsList)

        binding.rvIngredients.adapter = ingredientsAdapter
        binding.rvInstructions.adapter = instructionsAdapter

        setUpAppBar(true, "", true)
    }

    private fun getRecipeDetails(id: Int) = listOfRecipe[id]

    companion object {
        fun newInstance(recipe: Recipe) =
            RecipeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.Key.RECIPE, recipe)
                }
            }
    }

    private fun getRecipe(): Recipe {
        arguments?.let {
            recipe = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(Constants.Key.RECIPE, Recipe::class.java)!!
            } else {
                it.getParcelable(Constants.Key.RECIPE)!!
            }
        }
        return recipe
    }

    private fun getIngredients(recipe: Recipe): List<String> {
        return recipe.ingredients.split(";")
    }

    private fun getInstructions(recipe: Recipe): List<String> {
        return recipe.instructions.split(".").map { it.trim() }
    }

    private fun hideBottomNavigation() {
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.visibility = View.GONE
    }
}