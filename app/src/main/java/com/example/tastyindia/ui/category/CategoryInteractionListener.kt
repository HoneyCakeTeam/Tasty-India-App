package com.example.tastyindia.ui.category

import com.example.tastyindia.data.domain.enums.SeeAllRecipesType


interface CategoryInteractionListener{
    fun onClickRecipe(recipeId: Int)
    fun onClickSeeAll(type: SeeAllRecipesType)
}