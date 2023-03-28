package com.example.tastyindia.data.domain

import com.example.tastyindia.data.domain.enums.HomeItemType

data class HomeItem<T>(
    val item: T,
    val type: HomeItemType
)
