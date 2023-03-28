package com.example.tastyindia.ui.categorydetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemRecipeOfCategoryBinding

/**
 * Created by Aziza Helmy on 3/24/2023.
 */
class RecipesAdapter(
    private var recipes: ArrayList<Recipe>,
    private val listener: RecipeInteractionListener
) :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    //private var recipes = arrayListOf<Recipe>()

    fun setData(newList: ArrayList<Recipe>) {
        recipes = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories_recycler, parent, false)
        return RecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.binding.apply {
            tvRecipeName.text = currentRecipe.recipeName
            tvKitchenName.text = currentRecipe.cuisine
            Glide.with(root).load(currentRecipe.imageUrl).into(ivRecipe)
        }
        holder.itemView.setOnClickListener {
            listener.onClickItem(recipes[position])
        }
    }

    override fun getItemCount(): Int = recipes.size

    class RecipesViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemRecipeOfCategoryBinding.bind(itemView)
    }

    interface RecipeInteractionListener {
        fun onClickItem(recipe: Recipe)
    }
}