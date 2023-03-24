package com.example.tastyindia.ui.seeall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemRecipeSquaredBinding

class SeeAllRecipesAdapter(
    private val RecipeList: List<Recipe>,
    private val listener: RecipeInteractionListener
) :
    RecyclerView.Adapter<SeeAllRecipesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_squared, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentRecipe = RecipeList[position]

        holder.binding.apply {
            tvRecipeName.text = currentRecipe.recipeName
            Glide
                .with(holder.binding.root)
                .load(currentRecipe.imageUrl)
                .placeholder(R.drawable.ic_error)
                .into(ivRecipeImage)
            root.setOnClickListener { listener.onClickItem(currentRecipe) }
        }
    }

    override fun getItemCount() = RecipeList.size

    class CategoryViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemRecipeSquaredBinding.bind(itemView)
    }
}