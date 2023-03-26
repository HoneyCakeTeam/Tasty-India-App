package com.example.tastyindia.ui.category.categoryAdapters

import com.example.tastyindia.data.domain.Recipe

interface BaseCategoryInteractionListener{
    fun onClickRecipe(recipe: Recipe)
}
