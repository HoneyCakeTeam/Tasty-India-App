package com.example.tastyindia.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemSearchSquaredBinding

class SearchAdapter(
    private var RecipesList: List<Recipe>,
    private val listener: RecipeInteractionListener,
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_squared, parent, false)
        return SearchViewHolder(view)
    }

    fun setData(newRecipesList: List<Recipe>) {
        val diffCallback = RecipeDiffUtil(RecipesList, newRecipesList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        RecipesList = newRecipesList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentRecipe = RecipesList[position]
        holder.binding.apply {
            tvRecipeName.text = currentRecipe.recipeName
            tvRecipeCuisine.text = currentRecipe.cuisine
            Glide.with(holder.binding.root).load(currentRecipe.imageUrl)
                .placeholder(R.drawable.ic_error).into(ivRecipeImage)
            root.setOnClickListener { listener.onClickRecipe(currentRecipe) }
        }

    }

    override fun getItemCount() = RecipesList.size

    class SearchViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemSearchSquaredBinding.bind(itemView)
    }

    interface RecipeInteractionListener {
        fun onClickRecipe(recipe: Recipe)
    }

}