package com.example.tastyindia.data

import com.example.tastyindia.data.domain.Recipe

interface RecipeDataSource {
    fun getAllRecipes(): List<Recipe>
}