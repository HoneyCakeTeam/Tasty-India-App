package com.example.tastyindia.ui

import com.example.tastyindia.databinding.FragmentKitchenInfoBinding

class KitchenInfoFragment : BaseFragment<FragmentKitchenInfoBinding>() {
    override val TAG: String = "KITCHEN_INFO"

    override fun getViewBinding(): FragmentKitchenInfoBinding =
        FragmentKitchenInfoBinding.inflate(layoutInflater)

    override fun setUp() {

    }

    override fun addCallbacks() {
        getViewBinding().icBackKitchenInfo.setOnClickListener {

        }
    }
}