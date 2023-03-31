package com.example.tastyindia.ui.home


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import com.example.tastyindia.R
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.HomeItem
import com.example.tastyindia.data.domain.enums.HomeItemType
import com.example.tastyindia.data.domain.enums.SeeAllRecipesType
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentHomeBinding
import com.example.tastyindia.ui.HomeActivity
import com.example.tastyindia.ui.categorydetails.CategoryDetailsFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.ui.seeall.SeeAllRecipesFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.SharedPref
import com.example.tastyindia.utils.onClickBackFromNavigation
import com.example.tastyindia.utils.replaceFragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.snackbar.Snackbar

class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    HomeRecommendationAdapter.HomeRecommendationsListener,
    HomeRecipesOfTheWeekAdapter.RecipeOfTheWeekInteractionListener,
    HomeCategoriesAdapter.HomeCategoriesInteractionListener,
    HomeAdapter.HomeSeeAllListener {

    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    override val TAG: String = this::class.java.simpleName.toString()
    private var isClicked = false

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        SharedPref.sharedPref(requireContext())
//        if (SharedPref.loadNightModeState() == true) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        }
    }

    override fun setUp() {
        addCallbacks()

        val randomNumbersForRecommendations =
            dataManager.getRandomNumbersInListOfRecipe(
                (requireActivity() as HomeActivity).recommendationFirstRecipeId
            )
                .distinct().take(10)
        val listOfRecommendationRecipes =
            dataManager.getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)

        val randomNumbersForRecipesOfWeek =
            dataManager.getRandomNumbersInListOfRecipe(
                (requireActivity() as HomeActivity).recipesOfWeekFirstRecipeId
            ).distinct().take(10)
        val listOfRecipesOfWeek =
            dataManager.getListOfRecipeUsingRandomNumbers(randomNumbersForRecipesOfWeek)

        val listOfCategories = dataManager.getListOfHomeCategories()

        val itemList: MutableList<HomeItem<Any>> = mutableListOf()
        itemList.add(HomeItem("", HomeItemType.TYPE_HOME_WELCOME_HEADER))
        itemList.add(HomeItem(listOfCategories, HomeItemType.TYPE_HOME_CATEGORIES))
        itemList.add(
            HomeItem(
                listOfRecommendationRecipes,
                HomeItemType.TYPE_HOME_RECOMMENDATION_RECYCLE
            )
        )
        itemList.add(HomeItem(listOfRecipesOfWeek, HomeItemType.TYPE_RECIPES_OF_WEEK_RECYCLE))

        val adapter = HomeAdapter(
            itemList,
            this,
            this,
            this,
            this
        )
        binding.rvHome.adapter = adapter

    }

    private fun addCallbacks() {
        onClickBackFromNavigation()
    }

    override fun onClickCategory(categoryName: String) {
        val categoryDetailsFragment = CategoryDetailsFragment.newInstance(categoryName)
        replaceFragment(categoryDetailsFragment)
    }

    override fun onClickRecommendationRecipe(id: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(id)
        replaceFragment(recipeDetailsFragment)
    }

    override fun onClickRecipeOfWeek(id: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(id)
        replaceFragment(recipeDetailsFragment)
    }

    override fun onClickHomeSeeAll(type: SeeAllRecipesType) {
        val seeAllRecipesFragment = SeeAllRecipesFragment.newInstance(type)
        replaceFragment(seeAllRecipesFragment)
    }

    override fun onClickSwitchTheme(view: View) {
        if (!isClicked) {
            applyDarkMode()
            (view as ShapeableImageView).setImageResource(R.drawable.ic_dark)
            isClicked = true
        } else {
            applyLightMode()
            (view as ShapeableImageView).setImageResource(R.drawable.ic_light)
            isClicked = false
        }
    }

    private fun applyDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Snackbar.make(binding.root, "Night", Snackbar.LENGTH_SHORT).show()
        SharedPref.setNightModeState(true)
    }
    private fun applyLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Snackbar.make(binding.root, "Light", Snackbar.LENGTH_SHORT).show()
        SharedPref.setNightModeState(false)
    }

}