package com.example.tastyindia.data.domain.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class SeeAllRecipesType : Parcelable {
    TYPE_HOME_RECOMMENDATION,
    TYPE_RECIPES_OF_WEEK,
    TYPE_HEALTHY_CATEGORY,
    TYPE_FAST_CATEGORY,
    TYPE_EASY_CATEGORY
}