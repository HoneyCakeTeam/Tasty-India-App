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
        adapter = SearchAdapter(dataManager.searchByRecipeOrCuisine(""), this)
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
                query?.let { search(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { search(it) }
                return true
            }
        })
    }

    fun search(query: String?) {
        if (query!!.isNotEmpty()) {
            val result = dataManager.searchByRecipeOrCuisine(query)
            adapter.setData(result)
            binding.rvSearchResult.adapter = adapter
            log(result.size)
        } else if (query.isEmpty()) {
            adapter.setData(emptyList())
            binding.rvSearchResult.adapter = adapter
        }
    }

    private fun navigateToRecipeDetailsFragmentWithSelectedKitchenData(recipe: Recipe) {
        RecipeDetailsFragment.newInstance(recipe)
        Snackbar.make(binding.root, "$recipe Recipe ", Snackbar.LENGTH_LONG).show()
        replaceFragment(RecipeDetailsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

    override fun onClickRecipe(recipe: Recipe) {
        navigateToRecipeDetailsFragmentWithSelectedKitchenData(recipe)
    }
}