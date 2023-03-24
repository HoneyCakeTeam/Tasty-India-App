package com.example.tastyindia.ui.seeall

import com.example.tastyindia.data.domain.Recipe

interface RecipeInteractionListener {
    fun onClickItem(recipe: Recipe)
}