package com.example.tastyindia.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.example.tastyindia.data.domain.Recipe

class RecipeDiffUtil(val OldRecipesLis: List<Recipe>, val NewRecipesList: List<Recipe>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = OldRecipesLis.size

    override fun getNewListSize() = NewRecipesList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (OldRecipesLis[oldItemPosition].recipeName == NewRecipesList[newItemPosition].recipeName || OldRecipesLis[oldItemPosition].cuisine == NewRecipesList[newItemPosition].cuisine)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return true
    }
}