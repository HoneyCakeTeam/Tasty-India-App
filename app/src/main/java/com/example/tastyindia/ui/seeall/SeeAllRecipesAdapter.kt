package com.example.tastyindia.ui.seeall

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemRecipeSquaredBinding
import com.example.tastyindia.base.BaseAdapter
import com.example.tastyindia.base.BaseInteractionListener

class SeeAllRecipesAdapter(
    recipes: List<Recipe>,
    private val listener: RecipeInteractionListener
) : BaseAdapter<Recipe, ItemRecipeSquaredBinding>(recipes) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemRecipeSquaredBinding
        get() = ItemRecipeSquaredBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemRecipeSquaredBinding>,
        position: Int,
        currentItem: Recipe
    ) {
        holder.binding.apply {
            tvRecipeName.text = currentItem.recipeName
            Glide
                .with(holder.binding.root)
                .load(currentItem.imageUrl)
                .placeholder(R.drawable.ic_loading)
                .into(ivRecipeImage)
            root.setOnClickListener { listener.onClickRecipe(currentItem.id) }
        }
    }

    interface RecipeInteractionListener : BaseInteractionListener {
        fun onClickRecipe(recipeId: Int)
    }
}