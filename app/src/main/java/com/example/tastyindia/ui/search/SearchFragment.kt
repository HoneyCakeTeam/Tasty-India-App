package com.example.tastyindia.ui.search

import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentSearchBinding
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.onClickBackFromNavigation

class SearchFragment : BaseFragment<FragmentSearchBinding>(),
    SearchAdapter.RecipeInteractionListener, SearchView.OnQueryTextListener {

    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    private lateinit var adapter: SearchAdapter
    override val TAG: String = this::class.java.simpleName.toString()
    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun setUp() {
        adapter = SearchAdapter(this)
        addCallbacks()
    }

    private fun addCallbacks() {
        addSearchListener()
        onClickBackFromNavigation()
    }

    private fun addSearchListener() {
        binding.searchBar.setOnQueryTextListener(this)
    }

    private fun searchByQueryAndSetDataInAdapter(query: String?) {
        query?.let {
            binding.apply {
                visibilityOfImageAndRecyclerInSearchFragment(it)
                if (it.isNotEmpty()) {
                    setDataOnAdapter(it)
                }
            }
        }
    }

    private fun setDataOnAdapter(query: String) {
        val resultOfSearch = dataManager.searchByRecipeOrCuisine(query)
        adapter.setData(resultOfSearch)
        binding.rvSearchResult.adapter = adapter
    }

    private fun visibilityOfImageAndRecyclerInSearchFragment(query: String?) {
        val result = dataManager.searchByRecipeOrCuisine(query!!)
       with(binding) {
            imgSearch.setImageResource(if (query.isNotEmpty()) R.drawable.search_notfound else R.drawable.ic_recipe_book)
            rvSearchResult.visibility = if (query.isNotEmpty()) View.VISIBLE else View.GONE
            tvEmptySearch.text =if (result.isEmpty()) "No Recipes with this name!!" else ""
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { searchByQueryAndSetDataInAdapter(it) }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { searchByQueryAndSetDataInAdapter(it) }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null)
        transaction.commit()
    }

    override fun onClickRecipe(recipeId: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId)
        replaceFragment(recipeDetailsFragment)
    }

}