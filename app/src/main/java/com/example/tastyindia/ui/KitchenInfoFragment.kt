package com.example.tastyindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.databinding.FragmentKitchenInfoBinding

class KitchenInfoFragment : Fragment() {

    private lateinit var binding: FragmentKitchenInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKitchenInfoBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_kitchen_info, container, false)
    }
}