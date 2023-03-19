package com.example.tastyindia.ui

import com.example.tastyindia.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val TAG: String="HOME"

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
    }

    override fun addCallbacks() {
    }


}