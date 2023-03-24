package com.example.tastyindia.data.source

import android.content.Context
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.utils.Constants.Name.CSV_FILE_NAME
import com.example.tastyindia.utils.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader

class CsvDataSource(private val context: Context, private val parser: CsvParser) :
    RecipeDataSource {
    override fun getAllRecipes(): List<Recipe> {
        val recipeList = mutableListOf<Recipe>()
        context.apply {
            val inputStream = assets.open(CSV_FILE_NAME)
            val buffer = BufferedReader(InputStreamReader(inputStream))
            buffer.forEachLine {
                val currentRecipe = parser.parseLine(it)
                recipeList.add(currentRecipe)
            }
        }
        return recipeList
    }

}