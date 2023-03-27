package com.example.tastyindia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.HomeItem
import com.example.tastyindia.data.domain.HomeItemType
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemHomeHeaderTextBinding
import com.example.tastyindia.databinding.ItemRecommendationRecycleBinding
import com.example.tastyindia.databinding.ItemWeekRecipesBinding

class HomeAdapter(
    private val homeItem: List<HomeItem<Any>>,
    private val recommendationListener: HomeRecommendationAdapter.HomeRecommendationsListener,
    private val recipeOfWeekListener: HomeRecipesOfTheWeekAdapter.RecipeOfTheWeekInteractionListener
) : RecyclerView.Adapter<HomeAdapter.HomeBaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_WELCOME -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_header_text, parent, false)
                HomeGoodMorningViewHolder(view)
            }

            VIEW_TYPE_HOME_RECOMMENDATION_RECYCLE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recommendation_recycle, parent, false)
                HomeRecommendationViewHolder(view)
            }

            VIEW_TYPE_HOME_WEEK_RECYCLE ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_week_recipes, parent, false)
                HomeRecipesOfWeekViewHolder(view)
            }

            else -> throw java.lang.Exception("Execption in home")
        }

    }

    override fun getItemCount(): Int = homeItem.size

    override fun getItemViewType(position: Int): Int {
        return when (homeItem[position].type) {
            HomeItemType.TYPE_HOME_WELCOME_HEADER -> VIEW_TYPE_WELCOME
            HomeItemType.TYPE_HOME_RECOMMENDATION_RECYCLE -> VIEW_TYPE_HOME_RECOMMENDATION_RECYCLE
             HomeItemType.TYPR_RECIPES_OF_WEEK_RECYCLE -> VIEW_TYPE_HOME_WEEK_RECYCLE
            else -> super.getItemViewType(position)
        }
    }


    override fun onBindViewHolder(holder: HomeBaseViewHolder, position: Int) {
        when (holder) {
            is HomeGoodMorningViewHolder -> { }
            is HomeRecommendationViewHolder -> onBindRecommendationViewHolder(holder, position)
            is HomeRecipesOfWeekViewHolder -> onBindRecipesOfWeekViewHolder(holder, position)
        }
    }

    fun onBindRecommendationViewHolder(holder: HomeRecommendationViewHolder, position: Int) {
        val recipeList = homeItem[position].item as List<Recipe>
        val adapter = HomeRecommendationAdapter(recipeList, recommendationListener)
        holder.binding.rvHomeRecommendations.adapter = adapter
    }

    fun onBindRecipesOfWeekViewHolder(holder: HomeRecipesOfWeekViewHolder, position: Int) {
        val recipeList = homeItem[position].item as List<Recipe>
        val adapter = HomeRecipesOfTheWeekAdapter(recipeList,recipeOfWeekListener)
        holder.binding.rvHomeWeekOfWeek.adapter = adapter
    }

    abstract class HomeBaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class HomeGoodMorningViewHolder(viewItem: View) : HomeBaseViewHolder(viewItem) {
        val binding = ItemHomeHeaderTextBinding.bind(viewItem)
    }

    class HomeRecommendationViewHolder(viewItem: View) : HomeBaseViewHolder(viewItem) {
        val binding = ItemRecommendationRecycleBinding.bind(viewItem)
    }

    /*
        class HomeWelcomeViewHolder(viewItem: View) :HomeBaseViewHolder(viewItem) {
            val binding = ItemHomeRecipesOfWeekBinding.bind(viewItem)
        }
        */

    class HomeRecipesOfWeekViewHolder(viewItem: View) :HomeBaseViewHolder(viewItem) {
        val binding = ItemWeekRecipesBinding.bind(viewItem)
    }

    companion object {

        private const val VIEW_TYPE_WELCOME = 1
        private const val VIEW_TYPE_CATEGORIES = 2
        private const val VIEW_TYPE_HOME_RECOMMENDATION_RECYCLE = 3
        private const val VIEW_TYPE_HOME_WEEK_RECYCLE = 4
    }

}

