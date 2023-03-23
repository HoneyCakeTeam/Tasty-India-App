package com.example.tastyindia.ui

import android.os.Bundle
import com.example.tastyindia.databinding.FragmentCuisineDetailsBinding
import com.example.tastyindia.utils.Constants
import com.example.tastyindia.utils.Constants.KITCHEN_IMAGE_URL
import com.example.tastyindia.utils.Constants.KITCHEN_NAME

class KitchenDetailsFragment : BaseFragment<FragmentCuisineDetailsBinding>() {
    override val TAG: String = "CUISINEDETAILS"
    override fun getViewBinding() = FragmentCuisineDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        log(getRecipeName().toString())
    }

    override fun addCallbacks() {
        TODO("Not yet implemented")
    }

    private fun getRecipeName(): List<String> {
        return listOfRecipe
            .filter { it.cuisine == "Indian" }
            .map { it.recipeName }
            .take(10)
    }

    companion object {
        fun newInstance(kitchenName: String, kitchenImageUrl: String) =
            KitchenDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(KITCHEN_NAME, kitchenName)
                    putString(KITCHEN_IMAGE_URL, kitchenImageUrl)
                }
            }
    }
}
