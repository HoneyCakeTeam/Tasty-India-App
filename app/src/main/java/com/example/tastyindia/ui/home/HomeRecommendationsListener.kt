package com.example.tastyindia.ui.home

import com.example.tastyindia.data.domain.Recipe

interface HomeRecommendationsListener {
    fun onClickItem(recipe : Recipe)
}