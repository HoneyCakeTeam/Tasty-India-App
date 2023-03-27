package com.example.tastyindia.ui.recipedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R
import com.example.tastyindia.databinding.ItemIngredientsBinding
import com.example.tastyindia.databinding.ItemRecipeDetailsBinding

class RecipeDetailsAdapter(private val items: List<RecipeDetailsItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            RecipeDetailsItemType.HEADER.ordinal -> {
                val binding = ItemIngredientsBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding.root)
            }
            RecipeDetailsItemType.INGREDIENTS.ordinal -> {
                val binding = ItemRecipeDetailsBinding.inflate(inflater, parent, false)
                IngredientsViewHolder(binding.root)
            }
            RecipeDetailsItemType.INSTRUCTIONS.ordinal -> {
                val binding = ItemRecipeDetailsBinding.inflate(inflater, parent, false)
                InstructionsViewHolder(binding.root)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: RecipeDetailsItem
        when (holder) {
            is HeaderViewHolder -> {
                item = items[position] as RecipeDetailsItem.Header
                holder.binding.tvHeader.text = item.text
            }
            is IngredientsViewHolder -> {
                item = items[position] as RecipeDetailsItem.Ingredients
                holder.binding.tvRecipeDetails.text = item.text
            }
            is InstructionsViewHolder -> {
                item = items[position] as RecipeDetailsItem.Instructions
                holder.binding.tvRecipeDetails.text = item.text
            }
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].type.ordinal

    override fun getItemCount(): Int = items.size - 1

    sealed class RecipeDetailsItem(val type: RecipeDetailsItemType) {
        data class Header(val text: String) : RecipeDetailsItem(RecipeDetailsItemType.HEADER)
        data class Ingredients(val text: String) :
            RecipeDetailsItem(RecipeDetailsItemType.INGREDIENTS)

        data class Instructions(val text: String) :
            RecipeDetailsItem(RecipeDetailsItemType.INSTRUCTIONS)
    }

    enum class RecipeDetailsItemType {
        HEADER,
        INGREDIENTS,
        INSTRUCTIONS
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView)
    class HeaderViewHolder(itemView: View) : BaseViewHolder<RecipeDetailsItem.Header>(itemView) {
        val binding = ItemIngredientsBinding.bind(itemView)
    }

    class IngredientsViewHolder(itemView: View) :
        BaseViewHolder<RecipeDetailsItem.Ingredients>(itemView) {
        val binding = ItemRecipeDetailsBinding.bind(itemView)
    }

    class InstructionsViewHolder(itemView: View) :
        BaseViewHolder<RecipeDetailsItem.Instructions>(itemView) {
        val binding = ItemRecipeDetailsBinding.bind(itemView)
    }
}