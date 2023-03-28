package com.example.tastyindia.ui.kitchen

import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.KitchenInfo
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentKitchenInfoBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.utils.Constants.Key.KITCHEN_IMAGE_URL
import com.example.tastyindia.utils.Constants.Key.KITCHEN_NAME
import com.example.tastyindia.utils.CsvParser

class KitchenInfoFragment : BaseFragment<FragmentKitchenInfoBinding>() {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    override val TAG: String = this::class.simpleName!!
    private lateinit var kitchenName: String
    private lateinit var kitchenImageUrl: String
    private lateinit var kitchen: KitchenInfo

    override fun getViewBinding(): FragmentKitchenInfoBinding =
        FragmentKitchenInfoBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        // setUpAppBar(visibility = true, title = "Kitchen Info", showBackIcon = true)

        arguments?.let {
            kitchenName = it.getString(KITCHEN_NAME)!!
            kitchenImageUrl = it.getString(KITCHEN_IMAGE_URL)!!
        }
        Glide
            .with(binding.root)
            .load(kitchenImageUrl)
            .placeholder(R.drawable.ic_error)
            .into(binding.ivCardImage)

        kitchen = dataManager.getKitchenInfoByName(kitchenName)

        binding.tvHistoryTitle.text = "History Of $kitchenName Cuisine"
        binding.tvHistoryDescription.text = kitchen.historyDescription
        binding.tvRegionDescription.text = kitchen.RegionsDescription
        binding.tvDishesDescription.text = kitchen.dishesDescription
    }

    companion object {
        fun newInstance(kitchenName: String, kitchenImageURL: String) =
            KitchenInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(KITCHEN_NAME, kitchenName)
                    putString(KITCHEN_IMAGE_URL, kitchenImageURL)
                }
            }
    }

}