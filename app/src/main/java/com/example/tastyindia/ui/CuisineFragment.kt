package com.example.tastyindia.ui

import com.example.tastyindia.databinding.FragmentCuisineBinding

class CuisineFragment : BaseFragment<FragmentCuisineBinding>() {
    override val tag: String = "CuisineFragment"
    override fun getViewBinding(): FragmentCuisineBinding =
        FragmentCuisineBinding.inflate(layoutInflater)

    override fun setUp() {
        log(getAllCuisines().size.toString())
    }

    override fun addCallbacks() {

    }

    private fun getAllCuisines() = listOfRecipe.map { it.cuisine }.distinct()

}