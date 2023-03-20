package com.example.tastyindia.ui

import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.data.source.RecipeDataSource
import com.example.tastyindia.databinding.FragmentHomeBinding
import com.example.tastyindia.utils.CsvParser

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val TAG: String="HOME"

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        }

    override fun addCallbacks() {
    }




}