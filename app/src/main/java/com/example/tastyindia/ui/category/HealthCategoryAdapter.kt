package com.example.tastyindia.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemHealthCategoryBinding
import com.example.tastyindia.ui.BaseAdapter

class HealthCategoryAdapter(
    private val healthyRecipes: List<Recipe>,
    private val listener: MainCategoryAdapter.CategoryInteractionListener
) : BaseAdapter<Recipe, ItemHealthCategoryBinding>(healthyRecipes, listener) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemHealthCategoryBinding
        get() = ItemHealthCategoryBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemHealthCategoryBinding>,
        position: Int,
        currentItem: Recipe
    ) {
        holder.binding.apply {
            tvName.text = currentItem.recipeName
            Glide
                .with(holder.binding.root)
                .load(currentItem.imageUrl)
                .placeholder(R.drawable.ic_error)
                .into(healthImage)
            root.setOnClickListener {
                listener.onClickRecipe(currentItem.id)
            }
        }
    }

}


