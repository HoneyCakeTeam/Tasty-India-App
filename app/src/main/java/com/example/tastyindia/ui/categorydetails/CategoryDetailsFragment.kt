package com.example.tastyindia.ui.categorydetails

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tastyindia.R
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

    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    private lateinit var recipeAdapter: RecipesAdapter
    private lateinit var categoryName: String
    private lateinit var recipes: ArrayList<Recipe>

    override val TAG: String = this::class.simpleName.toString()
    override fun getViewBinding(): FragmentCategoryDetailsBinding =
        FragmentCategoryDetailsBinding.inflate(layoutInflater)

    override fun setUp() {

        getCategoryArgs()
        initRecyclerView()
        recipes = dataManager.getRecipesByCategory(categoryName)
        recipeAdapter.setData(recipes)

        Glide
            .with(binding.root)
            .load(recipes[0].imageUrl)
            .placeholder(R.drawable.ic_error)
            .into(binding.ivRecipe)

        addCallBacks()
    }

    private fun addCallBacks() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
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

    override fun onClickItem(recipeId: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId)
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