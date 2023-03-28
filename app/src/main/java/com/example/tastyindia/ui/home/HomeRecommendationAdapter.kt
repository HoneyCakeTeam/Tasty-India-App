package com.example.tastyindia.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemHomeRecommendationsBinding

class HomeRecommendationAdapter(val list: List<Recipe>, private val listener: HomeRecommendationsListener)
    : RecyclerView.Adapter<HomeRecommendationAdapter.HomeRecommendationViewHolder>() {

    class HomeRecommendationViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemHomeRecommendationsBinding.bind(viewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecommendationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_recommendations, parent, false)
        return HomeRecommendationViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecommendationViewHolder, position: Int) {
        val currentMeal = list[position]
        holder.binding.apply {
            tvHomeRecommendationRecipeName.text = currentMeal.recipeName
            tvHomeRecommendationKitchenName.text = currentMeal.cuisine
            Glide.with(root).load(currentMeal.imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(ivHomeRecommendationRecipeImage)

            root.setOnClickListener {
                listener.onClickRecommendationRecipe(currentMeal.id)
            }
        }
    }

    override fun getItemCount() = list.size
    interface HomeRecommendationsListener {
        fun onClickRecommendationRecipe(id: Int)
    }

}