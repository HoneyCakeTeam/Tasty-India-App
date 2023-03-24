package com.example.tastyindia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tastyindia.R
import com.bumptech.glide.Glide
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.HealthCategoriesBinding
import layout.CatecoryInteractorListener

class HealthCategoryAdabter(private val healthList: List<Recipe>,private val listener :CatecoryInteractorListener) :
    RecyclerView.Adapter<HealthCategoryAdabter.HealthViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HealthViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.health_categories,parent,false)
        return HealthViewHolder(view)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        val currentHealth = healthList[position]
        holder.binding.apply {
            tvName.text = currentHealth.recipeName
            Glide
                .with(holder.binding.root)
                .load(currentHealth.imageUrl)
                .into(healthImage)
            root.setOnClickListener {
                listener.onClickItem(currentHealth)
            }
        }

    }


    override fun getItemCount(): Int = healthList.size

    class HealthViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = HealthCategoriesBinding.bind(viewItem)
    }
}


