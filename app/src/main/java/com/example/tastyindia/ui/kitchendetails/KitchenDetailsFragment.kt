package com.example.tastyindia.ui.kitchendetails

import android.os.Bundle
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
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
import com.example.tastyindia.utils.replaceFragment

class KitchenDetailsFragment : BaseFragment<FragmentKitchenDetailsBinding>(),
    RecipeAdapter.RecipeInteractionListener {

    override val TAG: String = "CUISINEDETAILS"
    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var kitchenName: String
    private lateinit var kitchenImageUrl: String

    override fun getViewBinding(): FragmentKitchenDetailsBinding =
        FragmentKitchenDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        arguments?.let {
            kitchenName = it.getString(KITCHEN_NAME).toString()
            kitchenImageUrl = it.getString(KITCHEN_IMAGE_URL).toString()
        }
        recipeAdapter = RecipeAdapter(dataManager.getRecipesByKitchen(kitchenName), this)
        binding.rvRecipe.adapter = recipeAdapter
        setUpAppBar(true, kitchenName, true, true)
        onClickBack()
        onClickInfo()
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
        val btn_back = requireActivity().findViewById<ImageButton>(R.id.button_navDirection)
        btn_back.setOnClickListener { replaceFragment(KitchenFragment()) }
    }

    override fun onClickInfo() {
        val btn_info = requireActivity().findViewById<ImageButton>(R.id.button_info)
        btn_info.setOnClickListener { replaceFragment(KitchenInfoFragment()) }
    }

    override fun onClickRecipe(recipe: Recipe) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipe)
        replaceFragment(recipeDetailsFragment)
    }

}
