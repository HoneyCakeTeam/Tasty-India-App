package com.example.tastyindia.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemHomeRecipesOfWeekBinding
import com.example.tastyindia.ui.base.BaseAdapter
import com.example.tastyindia.ui.base.BaseInteractionListener

class HomeRecipesOfTheWeekAdapter(
    private val weekRecipes: List<Recipe>,
    private val listener: RecipeOfTheWeekInteractionListener
) : BaseAdapter<Recipe, ItemHomeRecipesOfWeekBinding>(weekRecipes, listener) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemHomeRecipesOfWeekBinding
        get() = ItemHomeRecipesOfWeekBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemHomeRecipesOfWeekBinding>,
        position: Int,
        currentItem: Recipe
    ) {

        holder.binding.apply {
            tvHomeItemRecipesOfWeek.text = currentItem.recipeName

            Glide.with(root).load(currentItem.imageUrl)
                .error(R.drawable.ic_error)
                .into(imgRecipeOfWeek)

            root.setOnClickListener {
                listener.onClickRecipeOfWeek(currentItem.id)
            }
        }
    }

    interface RecipeOfTheWeekInteractionListener : BaseInteractionListener {
        fun onClickRecipeOfWeek(id: Int)
    }
}