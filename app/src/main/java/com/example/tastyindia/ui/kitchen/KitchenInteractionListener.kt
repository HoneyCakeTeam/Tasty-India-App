package com.example.tastyindia.ui.kitchen

import com.example.tastyindia.data.domain.Recipe

interface KitchenInteractionListener {
    fun onClickItem(recipe: Recipe)
}