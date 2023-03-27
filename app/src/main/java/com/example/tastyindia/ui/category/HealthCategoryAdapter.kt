package com.example.tastyindia.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemHealthCategoryBinding

class HealthCategoryAdapter(
    private val healthList: List<Recipe>,
    private val listener: CategoryInteractionListener
) :
    RecyclerView.Adapter<HealthCategoryAdapter.HealthViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HealthViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_health_category, parent, false)
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
                listener.onClickRecipe(currentHealth)
            }
        }

    }

    override fun getItemCount(): Int = healthList.size

    class HealthViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = ItemHealthCategoryBinding.bind(viewItem)
    }
}


