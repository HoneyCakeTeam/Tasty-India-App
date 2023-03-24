package com.example.tastyindia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R

class IngredientsAdapter(private val ingredientsList: List<String>) : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingerdient, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.textIngredients.text = ingredientsList[position]
    }

    class IngredientsViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem) {
        val textIngredients : TextView = viewItem.findViewById(R.id.tv_ingredients)
    }
}


