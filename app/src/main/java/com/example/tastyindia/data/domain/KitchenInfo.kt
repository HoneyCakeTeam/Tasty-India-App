package com.example.tastyindia.data.domain

import com.example.tastyindia.R
import com.example.tastyindia.ui.KitchenInfoFragment

data class KitchenInfo(
    var kitchenName: String,
    var kitchenImage: Int,
    var historyDescription: String,
    var RegionsDescription: String,
    var dishesDescription: String
)

val indianKitchen = KitchenInfo(
    "Indian Cuisine",
    R.drawable.card_image,
    KitchenInfoFragment().getString(R.string.indian_history),
    KitchenInfoFragment().getString(R.string.region_desc),
    KitchenInfoFragment().getString(R.string.indian_dishes)
)


