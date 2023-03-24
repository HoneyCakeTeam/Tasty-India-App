package com.example.tastyindia.ui

import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {
    override val TAG = "RecipeDetails"

    override fun getViewBinding() = FragmentRecipeDetailsBinding.inflate(layoutInflater)


    override fun setUp() {
        setUpAppBar(true, "", true)

        val recipe = getRecipeDetails(1)
        val recipeName = recipe.recipeName
        log(recipeName)

        val ingredientsList = getIngredients(recipe)
        val instructionsList = getInstructions(recipe)

        val ingredientsAdapter = IngredientsAdapter(ingredientsList)
        val instructionsAdapter = InstructionsAdapter(instructionsList)

        binding.rvIngredients.adapter = ingredientsAdapter
        binding.rvInstructions.adapter = instructionsAdapter

        // set Image
        Glide.with(binding.root).load(recipe.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(binding.ivRecipe)
    }

    override fun addCallbacks() {
        TODO("Not yet implemented")
    }

    private fun getRecipeDetails(id: Int) = listOfRecipe[id]

    private fun getIngredients(recipe: Recipe): List<String> {
        return recipe.ingredients.split(";")
    }

    private fun getInstructions(recipe: Recipe): List<String> {
        return recipe.instructions.split(".").map { it.trim() }
    }

}