package com.example.tastyindia.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemFastFoodCategoryBinding

class FastFoodCategoryAdapter(private val fastList: List<Recipe>, private val listener : CategoryInteractionListener) :
    RecyclerView.Adapter<FastFoodCategoryAdapter.FastFoodViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FastFoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fast_food_category, parent, false)
        return FastFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FastFoodViewHolder, position: Int) {
        val currentFastFood = fastList[position]
        holder.binding.apply {
            tvName.text = currentFastFood.recipeName
            tvTime.text = currentFastFood.totalTimeInMins.toString()
            Glide
                .with(holder.binding.root)
                .load(currentFastFood.imageUrl)
                .into(fastFoodImage)
            root.setOnClickListener {
                listener.onClickRecipe(currentFastFood)
            }
        }

    }


    override fun getItemCount(): Int = fastList.size

    class FastFoodViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = ItemFastFoodCategoryBinding.bind(viewItem)
    }
    interface CategoryInteractionListener {
        fun onClickRecipe(recipe: Recipe)
    }
}


