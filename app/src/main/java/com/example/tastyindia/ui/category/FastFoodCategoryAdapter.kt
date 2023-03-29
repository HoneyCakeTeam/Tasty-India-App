package com.example.tastyindia.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemFastFoodCategoryBinding
import com.example.tastyindia.ui.BaseAdapter

class FastFoodCategoryAdapter(
    private val fastRecipes: List<Recipe>,
    private val listener: MainCategoryAdapter.CategoryInteractionListener
) : BaseAdapter<Recipe, ItemFastFoodCategoryBinding>(fastRecipes, listener) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemFastFoodCategoryBinding
        get() = ItemFastFoodCategoryBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemFastFoodCategoryBinding>,
        position: Int,
        currentItem: Recipe
    ) {
        holder.binding.apply {
            tvName.text = currentItem.recipeName
            tvTime.text = currentItem.totalTimeInMinutes.toString()
            Glide
                .with(holder.binding.root)
                .load(currentItem.imageUrl)
                .placeholder(R.drawable.ic_error)
                .into(fastFoodImage)
            root.setOnClickListener {
                listener.onClickRecipe(currentItem.id)
            }
        }
    }

}


