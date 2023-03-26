package com.example.tastyindia.ui.recipedetails

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
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
        val recipe = retrieveRecipeFromArguments()
        val recipeName = recipe.recipeName

        val ingredientsList = dataManager.getIngredients(recipe)
        val instructionsList = dataManager.getInstructions(recipe)

        val items = mutableListOf<RecipeDetailsAdapter.RecipeDetailsItem>()

        items.add(createHeader(recipe))
        val ingredients = createIngredientsList(ingredientsList)
        items.addAll(ingredients)
        items.add(createSecondHeader())
        val instructions = createInstructionsList(instructionsList)
        items.addAll(instructions)

        val adapter = RecipeDetailsAdapter(items)

        binding.rvIngredients.adapter = adapter
        binding.rvIngredients.layoutManager = LinearLayoutManager(context)

        setUpAppBar(true, "", true)
        navigateToHomeFragment()

        setRecipeName(recipeName, binding.tvRecipeName)
        setTimeToCookRecipe(recipe.totalTimeInMins, binding.tvTimeToCookRecipe)
        setDifficultyLevel(recipe.totalTimeInMins, binding.tvDifficultyLevel)
        setRecipeImage(recipe.imageUrl, binding.ivRecipe)
    }
    private fun setRecipeName(recipeName: String, textView: TextView) {
        textView.text = recipeName
    }
    @SuppressLint("SetTextI18n")
    private fun setTimeToCookRecipe(totalTimeInMins: Int, textView: TextView) {
        textView.text = "$totalTimeInMins Minutes"
    }
    private fun setDifficultyLevel(totalTimeInMins: Int, textView: TextView) {
        textView.text = calculateRecipeDifficultyLevel(totalTimeInMins)
    }
    private fun setRecipeImage(imageUrl: String, imageView: ImageView) {
        Glide.with(imageView.context).load(imageUrl).placeholder(R.drawable.ic_error)
            .into(imageView)
    }

    fun createHeader(recipe: Recipe): RecipeDetailsAdapter.RecipeDetailsItem.Header {
        return RecipeDetailsAdapter.RecipeDetailsItem.Header("Ingredients  -  ${recipe.ingredientsCount}")
    }
    fun createSecondHeader(): RecipeDetailsAdapter.RecipeDetailsItem.Header {
        return RecipeDetailsAdapter.RecipeDetailsItem.Header("How to prepare")
    }
    fun createIngredientsList(ingredientsList: List<String>): List<RecipeDetailsAdapter.RecipeDetailsItem.Ingredients> {
        return ingredientsList.map {
            RecipeDetailsAdapter.RecipeDetailsItem.Ingredients(it)
        }
    }
    fun createInstructionsList(instructionsList: List<String>): List<RecipeDetailsAdapter.RecipeDetailsItem.Instructions> {
        return instructionsList.map {
            RecipeDetailsAdapter.RecipeDetailsItem.Instructions(it)
        }
    }

    companion object {
        fun newInstance(recipe: Recipe) =
            RecipeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.Key.RECIPE, recipe)
                }
            }
    }
    private fun hideBottomNavigation() {
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.visibility = View.GONE
    }
    fun navigateToHomeFragment() {
        requireActivity().findViewById<ImageButton>(R.id.button_navDirection).setOnClickListener {
            val homeFragment = HomeFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, homeFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }
    private fun retrieveRecipeFromArguments(): Recipe {
        return requireArguments().getParcelable(Constants.Key.RECIPE)
            ?: throw IllegalStateException("Missing recipe argument")
    }

    private fun calculateRecipeDifficultyLevel(numberOfMinutesToCook: Int) =
        when {
            numberOfMinutesToCook <= 20 -> "Easy"
            numberOfMinutesToCook <= 40 -> "Medium"
            else -> "Hard"
        }
}