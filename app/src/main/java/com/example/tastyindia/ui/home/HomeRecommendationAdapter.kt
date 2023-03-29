package com.example.tastyindia.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemHomeRecommendationsBinding
import com.example.tastyindia.base.BaseAdapter
import com.example.tastyindia.base.BaseInteractionListener

class HomeRecommendationAdapter(
    recommendedRecipes: List<Recipe>,
    private val listener: HomeRecommendationsListener
) : BaseAdapter<Recipe, ItemHomeRecommendationsBinding>(recommendedRecipes) {

    interface HomeRecommendationsListener : BaseInteractionListener {
        fun onClickRecommendationRecipe(id: Int)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemHomeRecommendationsBinding
        get() = ItemHomeRecommendationsBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemHomeRecommendationsBinding>,
        position: Int,
        currentItem: Recipe
    ) {
        holder.binding.apply {
            tvHomeRecommendationRecipeName.text = currentItem.recipeName
            tvHomeRecommendationKitchenName.text = currentItem.cuisine
            Glide.with(root).load(currentItem.imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(ivHomeRecommendationRecipeImage)

            root.setOnClickListener {
                listener.onClickRecommendationRecipe(currentItem.id)
            }
        }
    }

}