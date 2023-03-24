package com.example.tastyindia.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.domain.RecipeList
import com.example.tastyindia.databinding.FragmentSeeAllRecipesBinding
import com.example.tastyindia.utils.Constants.Key.RECIPE_NAME
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class SeeAllRecipesFragment : BaseFragment<FragmentSeeAllRecipesBinding>(),
    RecipeInteractionListener {
    private lateinit var adapter: RecipesAdapter
    override val TAG = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentSeeAllRecipesBinding =
        FragmentSeeAllRecipesBinding.inflate(layoutInflater)

    override fun setUp() {
        adapter = RecipesAdapter(listOf(), this)
        binding.rvRecipes.adapter = adapter
    }

    override fun addCallbacks() {

    }

    companion object {
        fun newInstance(recipes: RecipeList) =
            KitchenDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(RECIPE_NAME, recipes)
                }
            }
    }

    override fun onClickItem(recipe: Recipe) {
        navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipe)
    }

    private fun navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipe: Recipe) {
        //RecipeDetailsFragment.newInstance(recipe)
        Snackbar.make(binding.root, "${recipe.recipeName} Recipe ", Snackbar.LENGTH_SHORT).show()
        replaceFragment(RecipeDetailsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

}