package com.example.tastyindia.ui.search

import android.widget.SearchView
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentSearchBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.utils.CsvParser

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

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
}