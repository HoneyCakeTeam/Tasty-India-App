package com.example.tastyindia

interface RecipeDataSource {
    fun getAllRecipes(): List<Recipe>
}