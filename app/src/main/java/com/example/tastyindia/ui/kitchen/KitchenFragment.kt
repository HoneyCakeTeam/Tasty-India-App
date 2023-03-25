package com.example.tastyindia.ui.kitchen

import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentKitchenBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.kitchendetails.KitchenDetailsFragment
import com.example.tastyindia.utils.CsvParser
import com.google.android.material.snackbar.Snackbar

class KitchenFragment : BaseFragment<FragmentKitchenBinding>(),
    KitchenAdapter.KitchenInteractionListener {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var adapter: KitchenAdapter
    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentKitchenBinding =
        FragmentKitchenBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        setUpAppBar(true, "Cuisine", false)
        adapter = KitchenAdapter(dataManager.getAllKitchen(), this)
        binding.rvKitchen.adapter = adapter
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

    override fun onClickRecipe(recipe: Recipe) {
        navigateToKitchenDetailsFragmentWithSelectedKitchenData(recipe)
    }

}