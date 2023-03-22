package com.example.tastyindia.ui

import android.widget.SearchView
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override val TAG: String = "SearchFragment"
    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun setUp() {
    }

    override fun addCallbacks() {
        addSearchListener()
    }

    private fun addSearchListener() {
        binding?.searchBar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val result = searchByRecipeOrCuisine(newText!!)
                log(result.size.toString())
                return true
            }
        })
    }

    private fun searchByRecipeOrCuisine(searchWord: String): List<Recipe> {
        return listOfRecipe
            .filter {
                it.cuisine.lowercase().contains(searchWord.lowercase()) || it.recipeName.lowercase()
                    .contains(searchWord.lowercase())
            }
    }
}