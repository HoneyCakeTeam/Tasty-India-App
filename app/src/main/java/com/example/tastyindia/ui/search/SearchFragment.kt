package com.example.tastyindia.ui.search

import android.widget.SearchView
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentSearchBinding
import com.example.tastyindia.ui.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private lateinit var adapter: SearchAdapter
    override val TAG: String = "SearchFragment"
    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun setUp() {
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
                if (query!!.isNotEmpty()){
                    val result = searchByRecipeOrCuisine(query)
                    adapter = SearchAdapter(result)
                    binding.rvSearchResult.adapter=adapter
                    log(result.size.toString())
                }
                else if (query.isEmpty())
                {
                    adapter = SearchAdapter(emptyList())
                    binding.rvSearchResult.adapter = adapter
                    log("Empty List ")


                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                 if (newText!!.isNotEmpty()){
                    val result = searchByRecipeOrCuisine(newText)
                    adapter = SearchAdapter(result)
                    binding.rvSearchResult.adapter=adapter
                    log(result.size.toString())
                }
                else if (newText.isEmpty())
                 {
                    //val result = searchByRecipeOrCuisine(newText)
                     adapter = SearchAdapter(emptyList())
                     binding.rvSearchResult.adapter = adapter
                     log("Empty List ")


                }
                return true
            }
        })
    }

    private fun searchByRecipeOrCuisine(searchWord: String): List<Recipe> {

        return listOfRecipe
            .filter {
                it.cuisine.lowercase().contains(searchWord.lowercase())
                        || it.recipeName.lowercase().contains(searchWord.lowercase())
            }
    }
}