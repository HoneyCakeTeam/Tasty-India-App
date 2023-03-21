package com.example.tastyindia.ui

import com.example.tastyindia.databinding.FragmentCuisineBinding

class CuisineFragment : BaseFragment<FragmentCuisineBinding>() {
    override val TAG: String = "CuisineFragment"
    override fun getViewBinding(): FragmentCuisineBinding =
        FragmentCuisineBinding.inflate(layoutInflater)

    override fun setUp() {
        log(getAllCuisines().size.toString())
        getAllRecipes()
    }

    override fun addCallbacks() {

    }

    private fun getAllCuisines(): List<String> {
        return getAllRecipes().map{ it.cuisine }.distinct()
    }

}