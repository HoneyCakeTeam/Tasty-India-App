package com.example.tastyindia.ui.kitchen.view

import com.example.tastyindia.R
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentKitchenBinding
import com.example.tastyindia.ui.kitchen.presenter.KitchenPresenter
import com.example.tastyindia.ui.kitchen.presenter.KitchenPresenterInterface
import com.example.tastyindia.ui.kitchen.view.KitchenAdapter
import com.example.tastyindia.ui.kitchendetails.KitchenDetailsFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.onClickBackFromNavigation
import com.example.tastyindia.utils.replaceFragment

class KitchenFragment : BaseFragment<FragmentKitchenBinding>(),
    KitchenAdapter.KitchenInteractionListener , KitchenViewInterface {

    private lateinit var kitchenPresenter : KitchenPresenterInterface
    private lateinit var adapter: KitchenAdapter
    private lateinit var kitchenDetailsFragment: KitchenDetailsFragment
    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentKitchenBinding =
        FragmentKitchenBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(true, getString(R.string.kitchenPageTitle), false)
        kitchenPresenter = KitchenPresenter(DataManager(CsvDataSource(requireContext(), CsvParser())), this )

        // get data from presenter
        kitchenPresenter.getAllKitchenRecipes()
        // send data to presenter -> it can be after button clicked
        putFavoriteRecipesById(1)

        onClickBackFromNavigation()
    }

    private fun navigateToKitchenDetailsFragmentWithSelectedKitchenData(recipe: Recipe) {
        val kitchenName = recipe.cuisine
        val kitchenImageUrl = recipe.imageUrl
        kitchenDetailsFragment = KitchenDetailsFragment.newInstance(kitchenName, kitchenImageUrl)
        replaceFragment(kitchenDetailsFragment)
    }

    override fun onClickRecipe(recipe: Recipe) {
        navigateToKitchenDetailsFragmentWithSelectedKitchenData(recipe)
    }

    override fun getAllKitchenRecipes(list: List<Recipe>) {
        adapter = KitchenAdapter(list, this)
        binding.rvKitchen.adapter = adapter
    }

    override fun putFavoriteRecipesById(id: Int) {
        kitchenPresenter.putFavoriteRecipesById(id)
    }

}