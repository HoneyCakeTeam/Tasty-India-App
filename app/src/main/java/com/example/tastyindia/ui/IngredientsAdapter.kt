package com.example.tastyindia.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class IngredientsViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem) {
        val textIngredients : TextView = viewItem.findViewById(R.id.tv_ingredients)
    }
}

