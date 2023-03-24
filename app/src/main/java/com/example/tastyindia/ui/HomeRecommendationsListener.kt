package com.example.tastyindia.ui

import com.example.tastyindia.data.domain.Recipe

interface HomeRecommendationsListener {
    fun onClickItem(recipe : Recipe)
}