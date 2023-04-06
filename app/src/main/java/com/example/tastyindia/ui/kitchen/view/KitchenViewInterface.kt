package com.example.tastyindia.ui.kitchen.view

import com.example.tastyindia.data.domain.Recipe

interface KitchenViewInterface {

    fun getAllKitchenRecipes(list : List<Recipe>)

    fun putFavoriteRecipesById(id : Int)
}