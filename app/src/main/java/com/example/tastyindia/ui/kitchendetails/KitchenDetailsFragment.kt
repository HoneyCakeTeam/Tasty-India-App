package com.example.tastyindia.ui.kitchendetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentKitchenDetailsBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.kitchen.KitchenAdapter
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.Constants.Key.KITCHEN_IMAGE_URL
import com.example.tastyindia.utils.Constants.Key.KITCHEN_NAME
import com.google.android.material.bottomnavigation.BottomNavigationView

class KitchenDetailsFragment : BaseFragment<FragmentKitchenDetailsBinding>(), KitchenAdapter.KitchenDetailsInteractionListener {

    override val TAG: String = "CUISINEDETAILS"
   // private lateinit var recipeAdapter: KitchenDetailsAdapter
    private lateinit var kitchenName: String
    private lateinit var kitchenImageUrl: String

    override fun getViewBinding() = FragmentKitchenDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        arguments?.let {
            kitchenName = it.getString(KITCHEN_NAME).toString()
            kitchenImageUrl = it.getString(KITCHEN_IMAGE_URL).toString()
        }
        hideBottomNavigation()
        setUpAppBar(true, kitchenName, true)
        // recipeAdapter = KitchenDetailsAdapter(getRecipeName())
        //binding.rvRecipe.adapter = recipeAdapter
    }

    private fun getRecipeName(): List<Recipe> {
        return listOfRecipe.distinctBy { it.cuisine == kitchenName }
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

    override fun onClickRecipe(recipe: Recipe) {
        navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipe)

    }

    private fun navigateToRecipeDetailsFragmentWithSelectedRecipeData(recipe: Recipe) {
        val kitchenName = recipe.cuisine
        val kitchenImageUrl = recipe.imageUrl
        newInstance(kitchenName, kitchenImageUrl)
        replaceFragment(RecipeDetailsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }
    private fun hideBottomNavigation(){
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.visibility = View.GONE
    }
}
