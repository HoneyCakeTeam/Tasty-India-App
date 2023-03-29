package com.example.tastyindia.ui.seeall

import android.os.Build
import android.os.Bundle
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.domain.enums.SeeAllRecipesType
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentSeeAllRecipesBinding
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.ui.HomeActivity
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.Constants.Key.RECIPE_LIST
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.onClickBack
import com.example.tastyindia.utils.replaceFragment

class SeeAllRecipesFragment : BaseFragment<FragmentSeeAllRecipesBinding>(),
    SeeAllRecipesAdapter.RecipeInteractionListener {
    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    private lateinit var recipeType: SeeAllRecipesType
    private lateinit var adapter: SeeAllRecipesAdapter
    private lateinit var title: String
    private lateinit var recipes: List<Recipe>
    override val TAG = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentSeeAllRecipesBinding =
        FragmentSeeAllRecipesBinding.inflate(layoutInflater)

    override fun setUp() {
        getRecipesType()
        getList()
        setUpAppBar(true, title, true)
        adapter = SeeAllRecipesAdapter(recipes, this)
        binding.rvRecipes.adapter = adapter
        onClickBack()
    }

    private fun getList() {
        when (recipeType) {
            SeeAllRecipesType.TYPE_EASY_CATEGORY -> {
                recipes = dataManager.getEasyRecipes()
                title = getString(R.string.easy_food)
            }
            SeeAllRecipesType.TYPE_HEALTHY_CATEGORY -> {
                recipes = dataManager.getHealthyRecipes(dataManager.getHealthyIngredients())
                title = getString(R.string.healthy_food)
            }
            SeeAllRecipesType.TYPE_FAST_CATEGORY -> {
                recipes = dataManager.getFastFoodRecipes()
                title = getString(R.string.fast_food)
            }
            SeeAllRecipesType.TYPE_HOME_RECOMMENDATION -> {
                recipes = dataManager.getListOfRecipeUsingRandomNumbers(
                    dataManager.getRandomNumbersInListOfRecipe(
                        (requireActivity() as HomeActivity).recommendationFirstRecipeId
                    )
                )
                title = getString(R.string.recommendations)
            }
            SeeAllRecipesType.TYPE_RECIPES_OF_WEEK -> {
                recipes = dataManager.getListOfRecipeUsingRandomNumbers(
                    dataManager.getRandomNumbersInListOfRecipe(
                        (requireActivity() as HomeActivity).recipesOfWeekFirstRecipeId
                    )
                )
                title = getString(R.string.recipe_of_week)
            }
        }
    }

    private fun getRecipesType(): SeeAllRecipesType {
        arguments?.let {
            recipeType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(RECIPE_LIST, SeeAllRecipesType::class.java)!!
            } else {
                it.getParcelable(RECIPE_LIST)!!
            }
        }
        return recipeType
    }

    companion object {
        fun newInstance(type: SeeAllRecipesType) =
            SeeAllRecipesFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(RECIPE_LIST, type)
                }
            }
    }

    private fun navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipeId: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId)
        replaceFragment(recipeDetailsFragment)
    }

    override fun onClickRecipe(recipeId: Int) {
        navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipeId)
    }


}