package com.example.tastyindia.utils
import com.example.tastyindia.data.domain.Recipe

class CsvParser {

    fun parseLine(line: String): Recipe {
        val token = line.split(",")
        return Recipe(
            recipeName = token[Constants.ColumnIndex.recipeName],
            ingredients = token[Constants.ColumnIndex.ingredients],
            totalTime = token[Constants.ColumnIndex.totalTime].toInt(),
            cuisine = token[Constants.ColumnIndex.cuisine],
            instructions = token[Constants.ColumnIndex.instructions],
            url = token[Constants.ColumnIndex.url],
            cleanedIngredients = token[Constants.ColumnIndex.cleanedIngredients],
            imageUrl = token[Constants.ColumnIndex.imageUrl],
            ingredientsCount = token[Constants.ColumnIndex.ingredientsCount].toInt(),
        )
    }

}