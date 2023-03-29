package com.example.tastyindia.ui.seeall

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemRecipeSquaredBinding
import com.example.tastyindia.ui.BaseAdapter
import com.example.tastyindia.ui.BaseInteractionListener

class SeeAllRecipesAdapter(
    private val recipes: List<Recipe>,
    private val listener: RecipeInteractionListener
) : BaseAdapter<Recipe, ItemRecipeSquaredBinding>(recipes, listener) {

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
                .placeholder(R.drawable.ic_error)
                .into(ivRecipeImage)
            root.setOnClickListener { listener.onClickRecipe(currentItem.id) }
        }
    }

    interface RecipeInteractionListener : BaseInteractionListener {
        fun onClickRecipe(recipeId: Int)
    }
}