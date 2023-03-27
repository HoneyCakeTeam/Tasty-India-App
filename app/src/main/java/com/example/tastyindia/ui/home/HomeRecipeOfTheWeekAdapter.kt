package com.example.tastyindia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemHomeRecipesOfWeekBinding
import com.bumptech.glide.Glide

class HomeRecipesOfTheWeekAdapter(
    private val recipesList: List<Recipe>,
    private val listener:RecipeOfTheWeekInteractionListener
):
    RecyclerView.Adapter<HomeRecipesOfTheWeekAdapter.HomeRecipesOfTheWeekViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecipesOfTheWeekViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_recipes_of_week, parent,false)
        return HomeRecipesOfTheWeekViewHolder(view)
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: HomeRecipesOfTheWeekViewHolder, position: Int) {
        val currentRecipe = recipesList[position]
        holder.binding.apply {
            tvHomeItemRecipesOfWeek.text = currentRecipe.recipeName

            Glide.with(root).load(currentRecipe.imageUrl)
                .error(R.drawable.ic_error)
                .into(imgRecipeOfWeek)

            root.setOnClickListener {
                listener.onClickRecipeOfWeek(currentRecipe)
            }
        }

    }

    class HomeRecipesOfTheWeekViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding = ItemHomeRecipesOfWeekBinding.bind(itemView)
    }

    interface RecipeOfTheWeekInteractionListener{
        fun onClickRecipeOfWeek(recipe: Recipe)
    }
}