package com.example.tastyindia.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.base.BaseAdapter
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemEasyCategoryBinding

class EasyCategoryAdapter(
    easyRecipes: List<Recipe>,
    private val listener: MainCategoryAdapter.CategoryInteractionListener
) :
    BaseAdapter<Recipe, ItemEasyCategoryBinding>(easyRecipes) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemEasyCategoryBinding
        get() = ItemEasyCategoryBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemEasyCategoryBinding>,
        position: Int,
        currentItem: Recipe
    ) {
        holder.binding.apply {
            tvName.text = currentItem.recipeName
            tvCount.text = currentItem.ingredientsCount.toString()
            Glide
                .with(holder.binding.root)
                .load(currentItem.imageUrl)
                .placeholder(R.drawable.ic_error)
                .into(easyImage)

            root.setOnClickListener {
                listener.onClickRecipe(currentItem.id)
            }
        }
    }

}


