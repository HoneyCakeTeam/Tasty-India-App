package com.example.tastyindia.ui.category.categoryAdapters

import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.ui.category.CategoryItemType

interface CategoryInteractionListener{
    fun onClickRecipe(recipe: Recipe)
    fun onClickSeeAll(type:CategoryItemType)
}
