package com.example.tastyindia.ui.recipedetails

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.home.HomeFragment
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

        val ingredientsList = dataManager.getIngredients(recipe)
        val instructionsList = dataManager.getInstructions(recipe)


        val items = mutableListOf<RecipeDetailsAdapter.RecipeDetailsItem>()

        items.add(RecipeDetailsAdapter.RecipeDetailsItem.Header("Ingredients  -  ${recipe.ingredientsCount}"))

        ingredientsList.forEach {
            items.add(RecipeDetailsAdapter.RecipeDetailsItem.Ingredients(it))
        }

        items.add(RecipeDetailsAdapter.RecipeDetailsItem.Header("How to prepare"))

        instructionsList.forEach {
            items.add(RecipeDetailsAdapter.RecipeDetailsItem.Instructions(it))
        }

        val adapter = RecipeDetailsAdapter(items)

        binding.rvIngredients.adapter = adapter
        binding.rvIngredients.layoutManager = LinearLayoutManager(context)

        setUpAppBar(true, "", true)
        requireActivity().findViewById<ImageButton>(R.id.button_navDirection).setOnClickListener {
            val homeFragment = HomeFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, homeFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.tvRecipeName.text = recipeName
        binding.tvTimeToCookRecipe.text = "${recipe.totalTimeInMins} Minutes"

        binding.tvDifficultyLevel.text = setDifficultyLevel(recipe.totalTimeInMins)
        Glide.with(binding.root).load(recipe.imageUrl).placeholder(R.drawable.ic_error).into(binding.ivRecipe)
    }

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

    private fun setDifficultyLevel(numberOfMinutesToCook:Int): String{
        var difficultyLevel = ""
        if (numberOfMinutesToCook <= 20){
            difficultyLevel = "Easy"
        }else if(numberOfMinutesToCook in 21..40){
            difficultyLevel = "Medium"
        }else {
            difficultyLevel = "Hard"
        }
        return difficultyLevel
    }

}