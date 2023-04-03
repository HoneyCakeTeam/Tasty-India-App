package com.example.tastyindia.ui.kitchendetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemRecipeSquaredBinding
import com.example.tastyindia.base.BaseAdapter
import com.example.tastyindia.base.BaseInteractionListener

class KitchenDetailsAdapter(
    kitchenRecipes: List<Recipe>,
    private val listener: RecipeInteractionListener
) : BaseAdapter<Recipe, ItemRecipeSquaredBinding>(kitchenRecipes) {

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