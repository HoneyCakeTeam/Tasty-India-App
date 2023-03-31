package com.example.tastyindia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.HomeCategoriesModel
import com.example.tastyindia.data.domain.HomeItem
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.domain.enums.HomeItemType
import com.example.tastyindia.data.domain.enums.SeeAllRecipesType
import com.example.tastyindia.databinding.ItemCategoriesRecyclerBinding
import com.example.tastyindia.databinding.ItemHomeHeaderTextBinding
import com.example.tastyindia.databinding.ItemRecommendationRecycleBinding
import com.example.tastyindia.databinding.ItemWeekRecipesBinding
import com.example.tastyindia.utils.SharedPref
import java.time.LocalTime

@Suppress("UNCHECKED_CAST")
class HomeAdapter(
    private val homeItem: List<HomeItem<Any>>,
    private val recommendationListener: HomeRecommendationAdapter.HomeRecommendationsListener,
    private val recipeOfWeekListener: HomeRecipesOfTheWeekAdapter.RecipeOfTheWeekInteractionListener,
    private val categoriesListener: HomeCategoriesAdapter.HomeCategoriesInteractionListener,
    private val seeAllListener: HomeSeeAllListener

) : RecyclerView.Adapter<HomeAdapter.HomeBaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_WELCOME -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_header_text, parent, false)
                HomeGoodMorningViewHolder(view)
            }
            VIEW_TYPE_CATEGORIES -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_categories_recycler, parent, false)
                HomeCategoriesViewHolder(view)
            }
            VIEW_TYPE_HOME_RECOMMENDATION_RECYCLE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recommendation_recycle, parent, false)
                HomeRecommendationViewHolder(view)
            }

            VIEW_TYPE_HOME_WEEK_RECYCLE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_week_recipes, parent, false)
                HomeRecipesOfWeekViewHolder(view)
            }

            else -> throw java.lang.Exception("Exception in home")
        }

    }

    override fun getItemCount(): Int = homeItem.size

    override fun getItemViewType(position: Int): Int {
        return when (homeItem[position].type) {
            HomeItemType.TYPE_HOME_WELCOME_HEADER -> VIEW_TYPE_WELCOME
            HomeItemType.TYPE_HOME_CATEGORIES -> VIEW_TYPE_CATEGORIES
            HomeItemType.TYPE_HOME_RECOMMENDATION_RECYCLE -> VIEW_TYPE_HOME_RECOMMENDATION_RECYCLE
            HomeItemType.TYPE_RECIPES_OF_WEEK_RECYCLE -> VIEW_TYPE_HOME_WEEK_RECYCLE
        }
    }


    override fun onBindViewHolder(holder: HomeBaseViewHolder, position: Int) {
        when (holder) {
            is HomeGoodMorningViewHolder -> {
                onBindHomeGoodMorningViewHolder(holder, position)
            }
            is HomeCategoriesViewHolder -> onBindCategoriesViewHolder(holder, position)
            is HomeRecommendationViewHolder -> onBindRecommendationViewHolder(holder, position)
            is HomeRecipesOfWeekViewHolder -> onBindRecipesOfWeekViewHolder(holder, position)
        }
    }

    private fun onBindHomeGoodMorningViewHolder(holder: HomeGoodMorningViewHolder, position: Int) {
        holder.binding.apply {
            tvHomeGoodMorning.text = HomeFragment().getGreeting()
            SharedPref.sharedPref(root.context)
            if (SharedPref.loadNightModeState() == true) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                themeSwitch.setImageResource(R.drawable.ic_dark)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                themeSwitch.setImageResource(R.drawable.ic_light)
            }
            themeSwitch.setOnClickListener {
                seeAllListener.onClickSwitchTheme(themeSwitch)
            }

        }
    }

    private fun onBindCategoriesViewHolder(holder: HomeCategoriesViewHolder, position: Int) {
        val categoriesList = homeItem[position].item as List<HomeCategoriesModel>
        val adapter = HomeCategoriesAdapter(categoriesList, categoriesListener)
        holder.binding.rvHomeCategories.adapter = adapter
    }

    private fun onBindRecommendationViewHolder(
        holder: HomeRecommendationViewHolder,
        position: Int
    ) {
        val recipeList = homeItem[position].item as List<Recipe>
        val adapter = HomeRecommendationAdapter(recipeList, recommendationListener)
        holder.binding.tvHomeRecommendationSeeAll.setOnClickListener {
            seeAllListener.onClickHomeSeeAll(SeeAllRecipesType.TYPE_HOME_RECOMMENDATION)
        }
        holder.binding.rvHomeRecommendations.adapter = adapter
    }

    private fun onBindRecipesOfWeekViewHolder(holder: HomeRecipesOfWeekViewHolder, position: Int) {
        val recipeList = homeItem[position].item as List<Recipe>
        val adapter = HomeRecipesOfTheWeekAdapter(recipeList, recipeOfWeekListener)
        holder.binding.tvHomeRSeeAll.setOnClickListener {
            seeAllListener.onClickHomeSeeAll(SeeAllRecipesType.TYPE_RECIPES_OF_WEEK)
        }
        holder.binding.rvHomeWeekOfWeek.adapter = adapter
    }

    abstract class HomeBaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)
    class HomeGoodMorningViewHolder(viewItem: View) : HomeBaseViewHolder(viewItem) {
        val binding = ItemHomeHeaderTextBinding.bind(viewItem)
    }

    class HomeCategoriesViewHolder(viewItem: View) : HomeBaseViewHolder(viewItem) {
        val binding = ItemCategoriesRecyclerBinding.bind(viewItem)
    }

    class HomeRecommendationViewHolder(viewItem: View) : HomeBaseViewHolder(viewItem) {
        val binding = ItemRecommendationRecycleBinding.bind(viewItem)
    }

    class HomeRecipesOfWeekViewHolder(viewItem: View) : HomeBaseViewHolder(viewItem) {
        val binding = ItemWeekRecipesBinding.bind(viewItem)
    }

    companion object {

        private const val VIEW_TYPE_WELCOME = 1
        private const val VIEW_TYPE_CATEGORIES = 2
        private const val VIEW_TYPE_HOME_RECOMMENDATION_RECYCLE = 3
        private const val VIEW_TYPE_HOME_WEEK_RECYCLE = 4
    }


    interface HomeSeeAllListener {
        fun onClickHomeSeeAll(type: SeeAllRecipesType)
        fun onClickSwitchTheme(view:View)
    }

}

