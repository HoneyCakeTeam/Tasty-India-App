package com.example.tastyindia.data.domain.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class CategoryItemType : Parcelable {
    TYPE_POSTER_IMAGE,
    TYPE_HEALTHY_CATEGORY,
    TYPE_FAST_CATEGORY,
    TYPE_EASY_CATEGORY
}