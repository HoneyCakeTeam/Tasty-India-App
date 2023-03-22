package com.example.tastyindia.ui

import com.example.tastyindia.databinding.FragmentCuisineDetailsBinding

class CuisnieDetailsFragment : BaseFragment<FragmentCuisineDetailsBinding>() {
    override val TAG: String = "CUISINEDETAILS"
    override fun getViewBinding() = FragmentCuisineDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        log(getRecipeName().toString())
    }

    override fun addCallbacks() {
        TODO("Not yet implemented")
    }

    private fun getRecipeName(): List<String> {
        return getAllRecipes()
            .filter { it.cuisine == "Indian" }
            .map { it.recipeName }
            .take(10)
    }
}
