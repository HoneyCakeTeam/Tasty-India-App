package com.example.tastyindia.ui.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.base.BaseAdapter
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemFastFoodCategoryBinding

class FastFoodCategoryAdapter(
    fastRecipes: List<Recipe>,
    private val listener: MainCategoryAdapter.CategoryInteractionListener
) : BaseAdapter<Recipe, ItemFastFoodCategoryBinding>(fastRecipes) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemFastFoodCategoryBinding
        get() = ItemFastFoodCategoryBinding::inflate

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemFastFoodCategoryBinding>,
        position: Int,
        currentItem: Recipe
    ) {
        holder.binding.apply {
            tvName.text = currentItem.recipeName
            tvTime.text = "${currentItem.totalTimeInMinutes} ${root.context.getString(R.string.minuite)}"
            Glide
                .with(holder.binding.root)
                .load(currentItem.imageUrl)
                .placeholder(R.drawable.ic_loading)
                .into(fastFoodImage)
            root.setOnClickListener {
                listener.onClickRecipe(currentItem.id)
            }
        }
    }

}


