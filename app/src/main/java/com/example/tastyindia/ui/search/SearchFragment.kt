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
            setImageAndTextVisibility(query)
            rvSearchResult.visibility = if (query.isNotEmpty()) View.VISIBLE else View.GONE
            if (result.isEmpty()) {
                tvEmptySearch.visibility = View.VISIBLE
                imgSearch.visibility = View.VISIBLE
                tvDetails.visibility = View.VISIBLE
                tvEmptySearch.text =
                    getString(R.string.no_recipes_with_this_name)
                tvDetails.text = getString(R.string.please_enter_valid_name)
                imgSearch.setImageResource(R.drawable.search_notfound)
            } else {
                tvEmptySearch.text = getString(R.string.dicover_recipes)
                tvDetails.text = getString(R.string.click_to_start_search)
            }
        }
    }

    private fun FragmentSearchBinding.setImageAndTextVisibility(query: String) {
        if (query.isEmpty()) {
            imgSearch.visibility = View.VISIBLE
            tvEmptySearch.visibility = View.VISIBLE
            tvDetails.visibility = View.VISIBLE
            imgSearch.setImageResource(R.drawable.ic_recipe_book)
        } else {
            imgSearch.visibility = View.GONE
            tvEmptySearch.visibility = View.GONE
            tvDetails.visibility = View.GONE
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