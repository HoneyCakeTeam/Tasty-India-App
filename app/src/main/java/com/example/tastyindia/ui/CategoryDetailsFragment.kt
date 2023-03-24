package com.example.tastyindia.ui

import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentCategoryDetailsBinding
import com.example.tastyindia.utils.Constants

class CategoryDetailsFragment : BaseFragment<FragmentCategoryDetailsBinding>(),
    RecipesAdapter.RecipeInteractionListener {

    private lateinit var recipeAdapter: RecipesAdapter
    private lateinit var categoryName: String
    private lateinit var categoryImage: String

    override val TAG: String = this::class.simpleName.toString()
    override fun getViewBinding(): FragmentCategoryDetailsBinding =
        FragmentCategoryDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        //TODO check args first then show data
        arguments?.let {
            categoryName = it.getString(Constants.Key.CATEGORY_NAME).toString()
            categoryImage = it.getString(Constants.Key.CATEGORY_IMAGE).toString()
        }
        initRecyclerView()
        recipeAdapter.setData(getAllRecipes() as ArrayList<Recipe>)
    }

    private fun initRecyclerView() {
        recipeAdapter = RecipesAdapter(requireContext(), this)
        binding.rvRecipe.adapter = recipeAdapter
    }

    override fun addCallbacks() {
        //TODO handle back btn
    }

    override fun onClickItem(recipe: Recipe) {
        RecipeDetailsFragment.newInstance(recipe.recipeName, recipe.imageUrl)
    }

    private fun getAllRecipes(): List<Recipe> {
        return listOfRecipe.distinctBy { it.cuisine }
    }

}