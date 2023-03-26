package com.example.tastyindia.ui.category.categoryAdapters

import com.example.tastyindia.data.domain.Recipe

interface CategoryInteractionListener{
    fun onClickRecipe(recipe: Recipe)
}
