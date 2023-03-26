package com.example.tastyindia.ui.kitchen

import android.os.Bundle
import android.widget.TextView
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
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

        val kitchen = dataManager.getKitchenInfoByName("European")

        view?.findViewById<TextView>(R.id.tv_history_title)?.text = kitchen[0].kitchenName
        view?.findViewById<TextView>(R.id.tv_history_description)?.text =
            kitchen[0].historyDescription
        view?.findViewById<TextView>(R.id.tv_region_description)?.text =
            kitchen[0].RegionsDescription
        view?.findViewById<TextView>(R.id.tv_dishes_description)?.text =
            kitchen[0].dishesDescription
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