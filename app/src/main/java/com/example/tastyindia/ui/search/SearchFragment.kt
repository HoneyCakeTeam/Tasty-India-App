package com.example.tastyindia.ui.search

import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentSearchBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.CsvParser

class SearchFragment : BaseFragment<FragmentSearchBinding>(),
    SearchAdapter.RecipeInteractionListener, SearchView.OnQueryTextListener {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var adapter: SearchAdapter
    override val TAG: String = "SearchFragment"
    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        adapter = SearchAdapter(this)
        addCallbacks()
    }

    private fun addCallbacks() {
        addSearchListener()
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
        binding.imgSearch.visibility = if (query.isNotEmpty()) View.GONE else View.VISIBLE
        binding.rvSearchResult.visibility = if (query.isNotEmpty()) View.VISIBLE else View.GONE
        binding.imgSearchNotFound.visibility = if (result.isEmpty()) View.VISIBLE else View.GONE
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