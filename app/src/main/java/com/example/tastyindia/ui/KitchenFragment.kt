package com.example.tastyindia.ui

import com.example.tastyindia.databinding.FragmentKitchenBinding

class KitchenFragment : BaseFragment<FragmentKitchenBinding>() {
    override val TAG: String = "CuisineFragment"
    override fun getViewBinding(): FragmentKitchenBinding =
        FragmentKitchenBinding.inflate(layoutInflater)

    override fun setUp() {
        log(getAllCuisines().size.toString())
    }

    override fun addCallbacks() {

    }

    private fun getAllCuisines() = listOfRecipe.map { it.cuisine }.distinct()

}