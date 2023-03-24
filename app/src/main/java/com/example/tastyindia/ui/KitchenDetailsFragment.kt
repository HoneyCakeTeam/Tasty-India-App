package com.example.tastyindia.ui

import android.os.Bundle
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentKitchenDetailsBinding
import com.example.tastyindia.utils.Constants.Key.KITCHEN_IMAGE_URL
import com.example.tastyindia.utils.Constants.Key.KITCHEN_NAME

class KitchenDetailsFragment : BaseFragment<FragmentKitchenDetailsBinding>() {

    override val TAG: String = "CUISINEDETAILS"
    private lateinit var recipeAdapter: KitchenDetailsAdapter
    private lateinit var kitchenName: String
    private lateinit var kitchenImageUrl: String

    override fun getViewBinding() = FragmentKitchenDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(true, kitchenName, true)
        arguments?.let {
            recipeAdapter = KitchenDetailsAdapter(getRecipeName())
            binding.rvRecipe.adapter = recipeAdapter
            kitchenName = it.getString(KITCHEN_NAME).toString()
            kitchenImageUrl = it.getString(KITCHEN_IMAGE_URL).toString()
        }
        log(getRecipeName().toString())
    }

    override fun addCallbacks() {
        TODO("Not yet implemented")
    }

    private fun getRecipeName(): List<Recipe> {
        return listOfRecipe.distinctBy { it.cuisine == kitchenName }

      /*  return listOfRecipe
            .filter { it.cuisine == kitchenName }
            .map { it.recipeName }
            .take(10)
*/    }


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
