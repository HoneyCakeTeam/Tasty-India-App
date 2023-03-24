package com.example.tastyindia.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeList(
    var recipeList: List<Recipe>
) : Parcelable
