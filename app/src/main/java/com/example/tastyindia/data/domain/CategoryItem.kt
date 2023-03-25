package com.example.tastyindia.data.domain

import com.example.tastyindia.data.domain.enums.CategoryItemType

data class CategoryItem (
    var list :List<Recipe>,
    var type : CategoryItemType
)