package com.example.tastyindia.ui.recipedetails

import android.os.Build
import android.os.Bundle
import android.view.View
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.utils.Constants
import com.example.tastyindia.utils.CsvParser
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    override val TAG = "RecipeDetails"
    private lateinit var recipe: Recipe

    override fun getViewBinding() = FragmentRecipeDetailsBinding.inflate(layoutInflater)


    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        hideBottomNavigation()
        val recipe = getRecipe()
        val recipeName = recipe.recipeName
        log(recipeName)

        val ingredientsList = dataManager.getIngredients(recipe)
        val instructionsList = dataManager.getInstructions(recipe)

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

    private fun hideBottomNavigation() {
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.visibility = View.GONE
    }
}