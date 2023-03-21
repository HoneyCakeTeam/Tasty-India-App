package com.example.tastyindia.ui

import com.example.tastyindia.databinding.FragmentKitchenBinding

class KitchenFragment : BaseFragment<FragmentKitchenBinding>(){
    override val TAG: String = "CuisineFragment"

    override fun getViewBinding(): FragmentKitchenBinding  = FragmentKitchenBinding.inflate(layoutInflater)

    override fun setUp() {

    }

    override fun addCallbacks() {

    }


}