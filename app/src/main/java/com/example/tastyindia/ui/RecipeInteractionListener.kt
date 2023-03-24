package com.example.tastyindia.ui

import com.example.tastyindia.data.domain.Recipe

/**
 * Created by Aziza Helmy on 3/24/2023.
 */
interface RecipeInteractionListener {
    fun onClickItem(recipe: Recipe)
}