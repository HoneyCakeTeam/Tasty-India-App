package com.example.tastyindia.data.source

import com.example.tastyindia.data.domain.Recipe

interface RecipeDataSource {
    fun getAllRecipes(): List<Recipe>
}