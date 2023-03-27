package com.example.tastyindia.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id : Int,
    val recipeName: String,
    val ingredients: String,
    val totalTimeInMinutes: Int,
    val cuisine: String,
    val instructions: String,
    val url: String,
    val cleanedIngredients: String,
    val imageUrl: String,
    val ingredientsCount: Int
) : Parcelable
