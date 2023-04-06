package com.example.tastyindia.ui.kitchen.presenter

import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.ui.kitchen.view.KitchenViewInterface

class KitchenPresenter (val dataManager: DataManagerInterface, val kitchenViewInterface : KitchenViewInterface) :KitchenPresenterInterface {

    override fun getAllKitchenRecipes() {
        val list = dataManager.getAllKitchenRecipes()
        kitchenViewInterface.getAllKitchenRecipes(list)
    }

    override fun putFavoriteRecipesById(id: Int) {
        //call function that insert data in local database or post data to remote source here and pass datat to it
        //dataManager.putFavoriteRecipesById(id)
    }


}