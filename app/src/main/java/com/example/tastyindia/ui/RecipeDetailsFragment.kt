package com.example.tastyindia.ui

import android.os.Bundle
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding
import com.example.tastyindia.utils.Constants

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {
    override val TAG = "RecipeDetails"

    override fun getViewBinding() = FragmentRecipeDetailsBinding.inflate(layoutInflater)


    override fun setUp() {
        val recipe = getRecipeDetails(1)
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
    private fun getIngredients(recipe: Recipe): List<String> {
        return recipe.ingredients.split(";")
    }

    private fun getInstructions(recipe: Recipe): List<String> {
        return recipe.instructions.split(".").map { it.trim() }
    }

}