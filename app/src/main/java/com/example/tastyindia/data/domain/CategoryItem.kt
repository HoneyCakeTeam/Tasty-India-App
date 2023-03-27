package com.example.tastyindia.data.domain

import com.example.tastyindia.data.domain.enums.CategoryItemType

data class CategoryItem<T> (
    val listItem :T,
    val type : CategoryItemType
)