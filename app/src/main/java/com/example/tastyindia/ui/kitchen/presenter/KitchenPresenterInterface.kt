package com.example.tastyindia.ui.kitchen.presenter

import com.example.tastyindia.data.domain.Recipe

interface KitchenPresenterInterface {

    fun getAllKitchenRecipes()
    fun putFavoriteRecipesById(id :Int)
}