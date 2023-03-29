package com.example.tastyindia.ui.kitchen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemKitchenBinding
import com.example.tastyindia.ui.BaseAdapter
import com.example.tastyindia.ui.BaseInteractionListener

class KitchenAdapter(
    private val kitchens: List<Recipe>,
    private val listener: KitchenInteractionListener
) : BaseAdapter<Recipe, ItemKitchenBinding>(kitchens, listener) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemKitchenBinding
        get() = ItemKitchenBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemKitchenBinding>,
        position: Int,
        currentItem: Recipe
    ) {
        holder.binding.apply {
            tvKitchenName.text = currentItem.cuisine
            Glide
                .with(holder.binding.root)
                .load(currentItem.imageUrl)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(imageKitchen)
            root.setOnClickListener { listener.onClickRecipe(currentItem) }
        }
    }

    interface KitchenInteractionListener : BaseInteractionListener {
        fun onClickRecipe(recipe: Recipe)
    }
}

