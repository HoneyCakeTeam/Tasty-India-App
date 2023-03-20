package com.example.tastyindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.data.source.RecipeDataSource
import com.example.tastyindia.databinding.FragmentSearchBinding
import com.example.tastyindia.utils.CsvParser

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private lateinit var parser: CsvParser
    private lateinit var datasource: RecipeDataSource

    override val TAG: String = "SearchFragment"

    override fun getViewBinding(): FragmentSearchBinding {
        TODO("Not yet implemented")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)

    }

    override fun setUp() {
        parser = CsvParser()
        datasource =CsvDataSource(requireContext(),parser)
        val list = getSixCuisineFromDataSet(datasource)
        val list2 =ex(datasource)
        log(list2.toString())

    }
    override fun addCallbacks() {
    }

    fun getSixCuisineFromDataSet(dataSource: RecipeDataSource):List<String>{
        return dataSource
            .getAllRecipes()
            .map { it.cuisine }
            .distinct()
            .take(6)
    }

    fun ex(dataSource: RecipeDataSource ): List<String>{
        return dataSource
            .getAllRecipes()
            .filter { it.cuisine == "Indian" }
            .map { it.recipeName}
            .take(10)
    }
}