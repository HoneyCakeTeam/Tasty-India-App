package com.example.tastyindia.ui.kitchendetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.KitchenInfo
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentKitchenDetailsBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.kitchen.KitchenFragment
import com.example.tastyindia.ui.kitchen.KitchenInfoFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.Constants.Key.KITCHEN_IMAGE_URL
import com.example.tastyindia.utils.Constants.Key.KITCHEN_NAME
import com.example.tastyindia.utils.CsvParser
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.log

class KitchenDetailsFragment : BaseFragment<FragmentKitchenDetailsBinding>(),
    RecipeAdapter.RecipeInteractionListener {

    override val TAG: String = "CUISINEDETAILS"
    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var kitchenName: String
    private lateinit var kitchenImageUrl: String

    override fun getViewBinding() = FragmentKitchenDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        arguments?.let {
            log(it.getString(KITCHEN_NAME).toString())
            log(it.getString(KITCHEN_IMAGE_URL).toString())
        }
        recipeAdapter = RecipeAdapter(dataManager.getRecipesByKitchen(KITCHEN_NAME), this)
        binding.rvRecipe.adapter =  recipeAdapter
        setUpAppBar(true, KITCHEN_NAME, true, true)
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

    override fun onClickBack() {
        navigateToKitchenFragment()
    }

    override fun onClickRecipe(recipe: Recipe) {
        navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipe)
    }

    override fun onClickInfo() {
        navigateToKitchenInfoFragmentWithSelectedRecipeData()
    }

    private fun navigateToKitchenFragment() {
        replaceFragment(KitchenFragment())
    }

    private fun navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipe: Recipe) {
//        val kitchenName = recipe.cuisine
//        val kitchenImageUrl = recipe.imageUrl
        newInstance(KITCHEN_NAME, KITCHEN_IMAGE_URL)
        replaceFragment(RecipeDetailsFragment())
    }

    private fun navigateToKitchenInfoFragmentWithSelectedRecipeData() {
        replaceFragment(KitchenInfoFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

    private fun hideBottomNavigation() {
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.visibility = View.GONE
    }
}
