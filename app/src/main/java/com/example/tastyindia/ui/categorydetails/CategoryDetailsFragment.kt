package com.example.tastyindia.ui.categorydetails

import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryDetailsBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.Constants
import com.example.tastyindia.utils.CsvParser

class CategoryDetailsFragment : BaseFragment<FragmentCategoryDetailsBinding>(),
    RecipesAdapter.RecipeInteractionListener {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var recipeAdapter: RecipesAdapter
    private lateinit var categoryName: String
    private lateinit var categoryImage: String

    override val TAG: String = this::class.simpleName.toString()
    override fun getViewBinding(): FragmentCategoryDetailsBinding =
        FragmentCategoryDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        arguments?.let {
            categoryName = it.getString(Constants.Key.CATEGORY_NAME).toString()
            categoryImage = it.getString(Constants.Key.CATEGORY_IMAGE).toString()
        }
        initRecyclerView()
        recipeAdapter.setData(dataManager.getAllKitchenRecipes() as ArrayList<Recipe>)
    }

    private fun initRecyclerView() {
        recipeAdapter = RecipesAdapter(requireContext(), this)
        binding.rvRecipe.adapter = recipeAdapter
    }

    override fun onClickItem(recipe: Recipe) {
      //  RecipeDetailsFragment.newInstance(recipe)
    }

}