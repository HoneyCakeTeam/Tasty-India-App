package com.example.tastyindia.ui.seeall

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.domain.enums.SeeAllRecipesType
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentSeeAllRecipesBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.HomeActivity
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.Constants.Key.RECIPE_LIST
import com.example.tastyindia.utils.CsvParser

class SeeAllRecipesFragment : BaseFragment<FragmentSeeAllRecipesBinding>(),
    SeeAllRecipesAdapter.RecipeInteractionListener {
    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    private lateinit var recipeType: SeeAllRecipesType
    private lateinit var adapter: SeeAllRecipesAdapter
    override val TAG = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentSeeAllRecipesBinding =
        FragmentSeeAllRecipesBinding.inflate(layoutInflater)

    override fun setUp() {
        getRecipesList()
        setUpAppBar(true, recipeType.name)
        adapter = SeeAllRecipesAdapter(getList(), this)
        binding.rvRecipes.adapter = adapter
    }

    private fun getList(): List<Recipe> {
        return when (recipeType) {
            SeeAllRecipesType.TYPE_EASY_CATEGORY -> dataManager.getEasyRecipes()
            SeeAllRecipesType.TYPE_HEALTHY_CATEGORY ->
                dataManager.getHealthyRecipes(dataManager.getHealthyIngredients())
            SeeAllRecipesType.TYPE_FAST_CATEGORY -> dataManager.getFastFoodRecipes()
            SeeAllRecipesType.TYPE_HOME_RECOMMENDATION -> dataManager.getListOfRecipeUsingRandomNumbers(
                dataManager.getRandomNumbersInListOfRecipe((requireActivity() as HomeActivity).recommendationFirstRecipeId)
            )
            SeeAllRecipesType.TYPE_RECIPES_OF_WEEK -> dataManager.getListOfRecipeUsingRandomNumbers(
                dataManager.getRandomNumbersInListOfRecipe((requireActivity() as HomeActivity).recipesOfWeekFirstRecipeId)
            )
        }
    }

    private fun getRecipesList(): SeeAllRecipesType {
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

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

    override fun onClickRecipe(recipeId: Int) {
        navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipeId)
    }

}