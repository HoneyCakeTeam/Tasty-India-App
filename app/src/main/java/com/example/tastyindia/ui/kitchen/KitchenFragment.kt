package com.example.tastyindia.ui.kitchen

import android.view.View
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentKitchenBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.kitchendetails.KitchenDetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class KitchenFragment : BaseFragment<FragmentKitchenBinding>(), KitchenInteractionListener {

    private lateinit var adapter: KitchenAdapter
    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentKitchenBinding =
        FragmentKitchenBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(true, "Cuisine", false)
        adapter = KitchenAdapter(getAllCuisines(), this)
        binding.rvKitchen.adapter = adapter
    }

    private fun getAllCuisines(): List<Recipe> {
        return listOfRecipe.distinctBy { it.cuisine }
    }

    override fun onClickItem(recipe: Recipe) {
        navigateToKitchenDetailsFragmentWithSelectedKitchenData(recipe)
    }

    private fun navigateToKitchenDetailsFragmentWithSelectedKitchenData(recipe: Recipe) {
        val kitchenName = recipe.cuisine
        val kitchenImageUrl = recipe.imageUrl
        KitchenDetailsFragment.newInstance(kitchenName, kitchenImageUrl)
        Snackbar.make(binding.root, "${recipe.cuisine} Kitchen ", Snackbar.LENGTH_LONG).show()
        replaceFragment(KitchenDetailsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

}