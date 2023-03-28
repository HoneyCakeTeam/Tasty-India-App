package com.example.tastyindia.ui.categorydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemRecipeOfCategoryBinding

/**
 * Created by Aziza Helmy on 3/24/2023.
 */
class RecipesAdapter(private val listener: RecipeInteractionListener) :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    private var recipes = arrayListOf<Recipe>()

    fun setData(newList: ArrayList<Recipe>) {
        recipes = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            ItemRecipeOfCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipes[position])
        holder.itemView.setOnClickListener {
            listener.onClickItem(recipes[position].id)
        }
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipesViewHolder(private val binding: ItemRecipeOfCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.apply {
                tvRecipeName.text = recipe.recipeName
                tvKitchenName.text = recipe.cuisine
                Glide.with(root).load(recipe.imageUrl).into(ivRecipe)
            }
        }

    }

    interface RecipeInteractionListener {
        fun onClickItem(recipeId: Int)
    }
}