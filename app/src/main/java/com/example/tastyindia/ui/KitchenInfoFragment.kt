package com.example.tastyindia.ui

import android.os.Bundle
import com.example.tastyindia.databinding.FragmentKitchenInfoBinding
import com.example.tastyindia.utils.Constants.Key.KITCHEN_IMAGE_URL
import com.example.tastyindia.utils.Constants.Key.KITCHEN_NAME

class KitchenInfoFragment : BaseFragment<FragmentKitchenInfoBinding>() {

    override val TAG: String = "KITCHEN_INFO"
    private lateinit var kitchenName: String
    private lateinit var kitchenImageUrl: String

    override fun getViewBinding(): FragmentKitchenInfoBinding =
        FragmentKitchenInfoBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(visibility = true, title = "Kitchen Info", showBackIcon = true)

        arguments?.let {
            kitchenName = it.getString(KITCHEN_NAME)!!
            kitchenImageUrl = it.getString(KITCHEN_IMAGE_URL)!!
        }
    }

    override fun addCallbacks() {

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