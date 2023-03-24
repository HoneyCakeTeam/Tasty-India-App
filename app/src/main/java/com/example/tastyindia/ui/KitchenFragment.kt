package com.example.tastyindia.ui

import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentKitchenBinding

class KitchenFragment : BaseFragment<FragmentKitchenBinding>() {

    private lateinit var adapter: KitchenAdapter
    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentKitchenBinding =
        FragmentKitchenBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(appbarVisibility = true, title = "Kitchens")
        adapter = KitchenAdapter(getAllCuisines())
        binding.rvKitchen.adapter = adapter
    }

    override fun addCallbacks() {

    }

    private fun getAllCuisines(): List<Recipe> {
        return listOfRecipe.distinctBy { it.cuisine }
    }

}