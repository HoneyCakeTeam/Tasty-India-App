package com.example.tastyindia.data

import android.content.Context
import com.example.tastyindia.utils.RecipeDataSource
import com.example.tastyindia.utils.Constants
import java.io.BufferedReader
import java.io.InputStreamReader

class CsvDataSource(private val context: Context, private val parser: CsvParser) :
    RecipeDataSource {
    override fun getAllRecipes(): List<Recipe> {
        val recipeList = mutableListOf<Recipe>()
        context.apply {
            val inputStream = assets.open(Constants.FILE_NAME)
            val buffer = BufferedReader(InputStreamReader(inputStream))
            buffer.forEachLine {
                val currentRecipe = parser.parseLine(it)
                recipeList.add(currentRecipe)
            }
        }
        return recipeList
    }

}