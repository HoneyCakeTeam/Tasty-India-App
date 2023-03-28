package com.example.tastyindia.utils

object Constants {
    object Name {
        const val CSV_FILE_NAME = "indian_food_v3.csv"
    }

    object Key {
        const val KITCHEN_NAME = "kitchenName"
        const val KITCHEN_IMAGE_URL = "kitchenImageUrl"
        const val RECIPE_NAME = "recipeName"
        const val RECIPE_URL = "recipeUrl"
        const val RECIPE_ID = 1
        const val RECIPE = "recipe"
        const val RECIPE_LIST="RecipeList"
    }

    object ColumnIndex {
        const val recipeName = 0
        const val ingredients = 1
        const val totalTime = 2
        const val cuisine = 3
        const val instructions = 4
        const val url = 5
        const val cleanedIngredients = 6
        const val imageUrl = 7
        const val ingredientsCount = 8
    }
}