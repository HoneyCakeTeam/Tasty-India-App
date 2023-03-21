package com.example.tastyindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tastyindia.R
import com.example.tastyindia.databinding.FragmentCuisineDetailsBinding

class CuisnieDetailsFragment : BaseFragment<FragmentCuisineDetailsBinding> (){
    override val TAG: String = "CUISINEDETAILS"
    override fun getViewBinding()=FragmentCuisineDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        log(getRecipeName().toString())
    }

    override fun addCallbacks() {
        TODO("Not yet implemented")
    }

    private fun getRecipeName(): List<String> {
        return listOfRecipe
            .filter { it.cuisine == "Indian" }
            .map{it.recipeName}
            .take(10)
    }
}