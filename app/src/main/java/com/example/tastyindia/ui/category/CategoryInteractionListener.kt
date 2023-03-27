package com.example.tastyindia.ui.category

import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.domain.enums.CategoryItemType


interface CategoryInteractionListener{
    fun onClickRecipe(recipe: Recipe)
    fun onClickSeeAll(type: CategoryItemType)
}