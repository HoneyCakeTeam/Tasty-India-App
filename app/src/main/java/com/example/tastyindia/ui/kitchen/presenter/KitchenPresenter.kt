package com.example.tastyindia.ui.kitchen.presenter

import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.ui.kitchen.view.KitchenViewInterface

class KitchenPresenter (val dataManager: DataManagerInterface, val kitchenViewInterface : KitchenViewInterface) :KitchenPresenterInterface {

    override fun getAllKitchenRecipes() {
        val list = dataManager.getAllKitchenRecipes()
        kitchenViewInterface.getAllKitchenRecipes(list)

    }

    override fun putFavoriteRecipesById(id: Int) {
        // send data to dataManager to insert data in local database or post data to remote source
        //dataManager.putFavoriteRecipesById(id)
    }


}