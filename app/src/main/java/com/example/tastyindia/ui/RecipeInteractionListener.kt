package com.example.tastyindia.ui

import com.example.tastyindia.data.domain.Recipe

interface RecipeInteractionListener {
    fun onClickItem(recipe: Recipe)
}