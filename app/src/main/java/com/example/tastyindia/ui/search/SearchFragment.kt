package com.example.tastyindia.ui.search

import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentSearchBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.kitchendetails.KitchenDetailsFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.CsvParser
import com.google.android.material.snackbar.Snackbar

class SearchFragment : BaseFragment<FragmentSearchBinding>(),
    SearchAdapter.RecipeInteractionListener {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var adapter: SearchAdapter
    override val TAG: String = "SearchFragment"
    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        addCallbacks()
        // setUpAppBar(visibility = true , title = "Search",showBackIcon = true)
        log(listOfRecipe.size)
    }

    private fun addCallbacks() {
        addSearchListener()
    }

    private fun addSearchListener() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.isNotEmpty()) {
                    val result = dataManager.searchByRecipeOrCuisine(query)
                    adapter = SearchAdapter(result)
                    binding.rvSearchResult.adapter = adapter
                    log(result.size.toString())
                } else if (query.isEmpty()) {
                    adapter = SearchAdapter(emptyList())
                    binding.rvSearchResult.adapter = adapter
                    log("Empty List ")


                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    val result = dataManager.searchByRecipeOrCuisine(newText)
                    adapter = SearchAdapter(result)
                    binding.rvSearchResult.adapter = adapter
                    log(result.size.toString())
                } else if (newText.isEmpty()) {
                    //val result = searchByRecipeOrCuisine(newText)
                    adapter = SearchAdapter(emptyList())
                    binding.rvSearchResult.adapter = adapter
                    log("Empty List ")


                }
                return true
            }
        })
    }

    private fun navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipe: Recipe) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipe)
        replaceFragment(recipeDetailsFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

    override fun onClickRecipe(recipe: Recipe) {
        navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipe)
    }
}