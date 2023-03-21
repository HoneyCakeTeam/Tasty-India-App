package com.example.tastyindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.databinding.FragmentKitchenInfoBinding

class KitchenInfoFragment : BaseFragment<FragmentKitchenInfoBinding>() {
    override val TAG: String = "KITCHEN_INFO"

    override fun getViewBinding(): FragmentKitchenInfoBinding =
        FragmentKitchenInfoBinding.inflate(layoutInflater)

    override fun setUp() {
        TODO("Not yet implemented")
    }

    override fun addCallbacks() {
        TODO("Not yet implemented")
    }
}