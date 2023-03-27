package com.example.tastyindia.utils

import com.example.tastyindia.data.domain.Recipe

class CsvParser {

    fun parseLine(line: String, id: Int): Recipe {
        val token = line.split(",")
        return Recipe(
            id = id,
            recipeName = token[Constants.ColumnIndex.recipeName],
            ingredients = token[Constants.ColumnIndex.ingredients],
            totalTimeInMinutes = token[Constants.ColumnIndex.totalTime].toInt(),
            cuisine = token[Constants.ColumnIndex.cuisine],
            instructions = token[Constants.ColumnIndex.instructions],
            url = token[Constants.ColumnIndex.url],
            cleanedIngredients = token[Constants.ColumnIndex.cleanedIngredients],
            imageUrl = token[Constants.ColumnIndex.imageUrl],
            ingredientsCount = token[Constants.ColumnIndex.ingredientsCount].toInt(),
        )
    }

}