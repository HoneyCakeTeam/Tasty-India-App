package com.example.tastyindia.ui.recipedetails

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.databinding.ItemIngredientsBinding
import com.example.tastyindia.databinding.ItemRecipeDetailsBinding
import com.example.tastyindia.databinding.RecipeDetailsHeaderBinding
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment.Companion.setCleanedIngredients
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment.Companion.setDifficultyLevel
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment.Companion.setRecipeImage
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment.Companion.setRecipeName
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment.Companion.setTimeToCookRecipe
import com.example.tastyindia.utils.Constants.ColumnIndex.ingredients

class RecipeDetailsAdapter(private val items: List<RecipeDetailsItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            RecipeDetailsItemType.HEADER.ordinal -> {
                val binding = RecipeDetailsHeaderBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding.root)
            }
            RecipeDetailsItemType.TITLE.ordinal -> {
                val binding = ItemIngredientsBinding.inflate(inflater, parent, false)
                TitleViewHolder(binding.root)
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
        val item = items[position]
        when (holder) {
            is HeaderViewHolder -> holder.bind(item as RecipeDetailsItem.Header)
            is TitleViewHolder -> holder.bind(item as RecipeDetailsItem.Title)
            is IngredientsViewHolder -> holder.bind(item as RecipeDetailsItem.Ingredients)
            is InstructionsViewHolder -> holder.bind(item as RecipeDetailsItem.Instructions)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    /* override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         val item: RecipeDetailsItem
         when (holder) {
             is HeaderViewHolder -> {
                 item = items[position] as RecipeDetailsItem.Header
                 holder.binding.ivRecipe =
                 holder.binding.tvRecipeName.text =
                 holder.binding.tvDifficultyLevel.text =
                 holder.binding.tvTimeToCookRecipe.text =
             }
             is TitleViewHolder -> {
                 item = items[position] as RecipeDetailsItem.Title
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
     }*/

    override fun getItemViewType(position: Int): Int = items[position].type.ordinal

    override fun getItemCount(): Int = items.size - 1

    sealed class RecipeDetailsItem(val type: RecipeDetailsItemType) {
        data class Header(
            val recipeImage: String,
            val recipeName: String,
            val minutesToCook: Int,
            val cleaned : Int
        ) : RecipeDetailsItem(RecipeDetailsItemType.HEADER)

        data class Title(val text: String) : RecipeDetailsItem(RecipeDetailsItemType.TITLE)
        data class Ingredients(val text: String) :
            RecipeDetailsItem(RecipeDetailsItemType.INGREDIENTS)

        data class Instructions(val text: String) :
            RecipeDetailsItem(RecipeDetailsItemType.INSTRUCTIONS)
    }

    enum class RecipeDetailsItemType {
        HEADER,
        TITLE,
        INGREDIENTS,
        INSTRUCTIONS
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView)
    class HeaderViewHolder(itemView: View) : BaseViewHolder<RecipeDetailsItem.Header>(itemView) {
        val binding = RecipeDetailsHeaderBinding.bind(itemView)
        fun bind(header: RecipeDetailsItem.Header) {
            binding.apply {
                setRecipeImage(header.recipeImage, ivRecipe)
                setRecipeName(header.recipeName, tvRecipeName)
                setTimeToCookRecipe(header.minutesToCook, tvTimeToCookRecipe)
                setDifficultyLevel(header.minutesToCook, tvDifficultyLevel)
                setCleanedIngredients(header.cleaned,tvCleaned)
            }
        }
    }

    class TitleViewHolder(itemView: View) : BaseViewHolder<RecipeDetailsItem.Title>(itemView) {
        val binding = ItemIngredientsBinding.bind(itemView)
        fun bind(title: RecipeDetailsItem.Title) {
            binding.apply {
                tvTitle.text = title.text
            }
        }
    }

    class IngredientsViewHolder(itemView: View) :
        BaseViewHolder<RecipeDetailsItem.Ingredients>(itemView) {
        val binding = ItemRecipeDetailsBinding.bind(itemView)
        fun bind(ingredients: RecipeDetailsItem.Ingredients) {
            binding.apply {
                tvRecipeDetails.text = ingredients.text
            }
        }
    }

    class InstructionsViewHolder(itemView: View) :
        BaseViewHolder<RecipeDetailsItem.Instructions>(itemView) {
        val binding = ItemRecipeDetailsBinding.bind(itemView)
        fun bind(instructions: RecipeDetailsItem.Instructions) {
            binding.apply {
                tvRecipeDetails.text = instructions.text
            }
        }
    }
}