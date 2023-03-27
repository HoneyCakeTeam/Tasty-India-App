package com.example.tastyindia.ui.categorydetails

import android.os.Bundle
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryDetailsBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.replaceFragment

class CategoryDetailsFragment : BaseFragment<FragmentCategoryDetailsBinding>(),
    RecipesAdapter.RecipeInteractionListener {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var recipeAdapter: RecipesAdapter
    private lateinit var categoryName: String

    override val TAG: String = this::class.simpleName.toString()
    override fun getViewBinding(): FragmentCategoryDetailsBinding =
        FragmentCategoryDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        getCategoryArgs()
        initRecyclerView()
        recipeAdapter.setData(dataManager.getAllKitchenRecipes() as ArrayList<Recipe>)//TODO GET RECIPE BY CATEGORY
    }

    private fun getCategoryArgs() {
        arguments?.let {
            categoryName = it.getString(CATEGORY_NAME).toString()
        }
    }

    private fun initRecyclerView() {
        recipeAdapter = RecipesAdapter(this)
        binding.rvRecipe.adapter = recipeAdapter
    }

    override fun onClickItem(recipe: Recipe) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(1)
        replaceFragment(recipeDetailsFragment)
    }

    companion object {
        private const val CATEGORY_NAME = "categoryName"
        fun newInstance(categoryName: String) =
            CategoryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY_NAME, categoryName)
                }
            }
    }

}