package com.example.tastyindia.ui

import com.example.tastyindia.data.domain.Recipe

interface KitchenInteractionListener {
    fun onClickItem(recipe: Recipe)
}