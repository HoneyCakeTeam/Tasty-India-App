package com.example.tastyindia.ui

import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentCategoryDetailsBinding

class CategoryDetailsFragment : BaseFragment<FragmentCategoryDetailsBinding>(),
    RecipesAdapter.RecipeInteractionListener {
    private lateinit var recipeAdapter: RecipesAdapter

    override val TAG: String = this::class.simpleName.toString()
    override fun getViewBinding(): FragmentCategoryDetailsBinding =
        FragmentCategoryDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        initRecyclerView()
        recipeAdapter.setData(getAllRecipes() as ArrayList<Recipe>)
    }

    private fun initRecyclerView() {
        recipeAdapter = RecipesAdapter(requireContext(), this)
        binding.rvRecipe.adapter = recipeAdapter
    }

    override fun addCallbacks() {}

    override fun onClickItem(recipe: Recipe) {
        RecipeDetailsFragment.newInstance(recipe.recipeName, recipe.imageUrl)
    }

    private fun getAllRecipes(): List<Recipe> {
        return listOfRecipe.distinctBy { it.cuisine }
    }

}