package com.example.tastyindia.ui

import android.os.Bundle
import com.example.tastyindia.data.domain.RecipeList
import com.example.tastyindia.databinding.FragmentSeeAllRecipesBinding
import com.example.tastyindia.utils.Constants.Key.RECIPE_NAME

class SeeAllRecipesFragment : BaseFragment<FragmentSeeAllRecipesBinding>() {
    private lateinit var adapter: RecipesAdapter
    override val TAG = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentSeeAllRecipesBinding =
        FragmentSeeAllRecipesBinding.inflate(layoutInflater)

    override fun setUp() {
        adapter = RecipesAdapter(listOf())
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

}