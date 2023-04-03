package com.example.tastyindia.ui.kitchen

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.KitchenInfo
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentKitchenInfoBinding
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.onClickBack

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
        onClickBack()

        arguments?.let {
            kitchenName = it.getString(KITCHEN_NAME)!!
            kitchenImageUrl = it.getString(KITCHEN_IMAGE_URL)!!
        }
        Glide
            .with(binding.root)
            .load(kitchenImageUrl)
            .placeholder(R.drawable.ic_loading)
            .into(binding.ivCardImage)

        kitchen = dataManager.getKitchenInfoByName(kitchenName)

        binding.tvHistoryTitle.text = "History Of $kitchenName Cuisine"
        binding.tvHistoryDescription.text = kitchen.historyDescription
        binding.tvRegionDescription.text = kitchen.regionsDescription
        binding.tvDishesDescription.text = kitchen.dishesDescription

        setUpAppBar(true, "$kitchenName History", showBackButton = true)
    }

    companion object {
        const val KITCHEN_NAME = "kitchenName"
        const val KITCHEN_IMAGE_URL = "kitchenImageUrl"
        fun newInstance(kitchenName: String, kitchenImageURL: String) =
            KitchenInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(KITCHEN_NAME, kitchenName)
                    putString(KITCHEN_IMAGE_URL, kitchenImageURL)
                }
            }
    }

}