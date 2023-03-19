package com.example.tastyindia.utils

import com.example.tastyindia.data.Recipe

interface RecipeDataSource {
    fun getAllRecipes(): List<Recipe>
}