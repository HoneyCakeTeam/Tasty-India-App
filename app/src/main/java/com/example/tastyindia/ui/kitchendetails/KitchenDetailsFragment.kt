package com.example.tastyindia.ui.kitchendetails

import android.os.Bundle
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentKitchenDetailsBinding
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.ui.kitchen.KitchenFragment
import com.example.tastyindia.ui.kitchen.KitchenInfoFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.Constants.Key.KITCHEN_IMAGE_URL
import com.example.tastyindia.utils.Constants.Key.KITCHEN_NAME
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.replaceFragment

class KitchenDetailsFragment : BaseFragment<FragmentKitchenDetailsBinding>(),
    KitchenDetailsAdapter.RecipeInteractionListener {

    override val TAG: String = this::class.java.simpleName.toString()
    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var recipeAdapter: KitchenDetailsAdapter
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

        Glide
            .with(binding.root)
            .load(kitchenImageUrl)
            .placeholder(R.drawable.ic_error)
            .into(binding.imageCuisine)

        recipeAdapter = KitchenDetailsAdapter(dataManager.getRecipesByKitchen(kitchenName), this)
        binding.rvRecipe.adapter = recipeAdapter
        setUpAppBar(true, kitchenName, showBackButton = true, showInfoButton = true)
        addCallbacks()
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

    private fun addCallbacks() {
        onClickBackNavigateToKitchenFragment()
        onClickInfoNavigateToKitchenInfoFragment()
    }

    private fun onClickBackNavigateToKitchenFragment() {
        val backButton = requireActivity().findViewById<ImageButton>(R.id.button_navDirection)
        backButton.setOnClickListener { replaceFragment(KitchenFragment()) }
    }

    private fun onClickInfoNavigateToKitchenInfoFragment() {
        val infoButton = requireActivity().findViewById<ImageButton>(R.id.button_info)
        val kitchenInfoFragment = KitchenInfoFragment.newInstance(kitchenName, kitchenImageUrl)
        infoButton.setOnClickListener { replaceFragment(kitchenInfoFragment) }
    }

    override fun onClickRecipe(recipeId: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId)
        replaceFragment(recipeDetailsFragment)
    }

}
