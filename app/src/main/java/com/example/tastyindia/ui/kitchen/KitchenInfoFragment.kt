package com.example.tastyindia.ui.kitchen

import android.os.Bundle
import android.widget.TextView
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.KitchenInfo
import com.example.tastyindia.data.domain.kitchens
import com.example.tastyindia.databinding.FragmentKitchenInfoBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.utils.Constants.Key.KITCHEN_IMAGE_URL
import com.example.tastyindia.utils.Constants.Key.KITCHEN_NAME

class KitchenInfoFragment : BaseFragment<FragmentKitchenInfoBinding>() {

    override val TAG: String = this::class.simpleName!!
    private lateinit var kitchenName: String
    private lateinit var kitchenImageUrl: String

    override fun getViewBinding(): FragmentKitchenInfoBinding =
        FragmentKitchenInfoBinding.inflate(layoutInflater)

    override fun setUp() {
       // setUpAppBar(visibility = true, title = "Kitchen Info", showBackIcon = true)

        arguments?.let {
            kitchenName = it.getString(KITCHEN_NAME)!!
            kitchenImageUrl = it.getString(KITCHEN_IMAGE_URL)!!
        }

        val kitchen = getKitchenInfo("European")

        view?.findViewById<TextView>(R.id.tv_history_title)?.text = kitchen[0].kitchenName
        view?.findViewById<TextView>(R.id.tv_history_description)?.text =
            kitchen[0].historyDescription
        view?.findViewById<TextView>(R.id.tv_region_description)?.text =
            kitchen[0].RegionsDescription
        view?.findViewById<TextView>(R.id.tv_dishes_description)?.text =
            kitchen[0].dishesDescription
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

    private fun getKitchenInfo(kitchenName: String): MutableList<KitchenInfo> {
        return kitchens
            .filter { it.kitchenName == kitchenName }
            .toMutableList()
    }
}