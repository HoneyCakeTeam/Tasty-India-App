package com.example.tastyindia.ui.home


import android.widget.Toast
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.HomeItem
import com.example.tastyindia.data.domain.HomeItemType
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentHomeBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.categorydetails.CategoryDetailsFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.ui.seeall.SeeAllRecipesFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.replaceFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    HomeRecommendationAdapter.HomeRecommendationsListener,
    HomeRecipesOfTheWeekAdapter.RecipeOfTheWeekInteractionListener,
    HomeCategoriesAdapter.HomeCategoriesInteractionListener,
    HomeAdapter.HomeSeeAllListener {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface

    override val TAG: String = "HomeFragment"

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)

        setUpAppBar(false)

        val randomNumbersForRecommendations =
            dataManager.getRandomNumbersInListOfRecipe().distinct().take(10)
        val listOfRecommendationRecipes =
            dataManager.getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)

        val randomNumbersForRecipesOfWeek =
            dataManager.getRandomNumbersInListOfRecipe().distinct().take(10)
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
        itemList.add(HomeItem(listOfRecipesOfWeek, HomeItemType.TYPR_RECIPES_OF_WEEK_RECYCLE))

        val adapter = HomeAdapter(itemList, this, this, this, this)
        binding.recyclevHome.adapter = adapter

    }

    override fun onClickCategory(categoryName: String) {
        CategoryDetailsFragment.newInstance(categoryName)
        replaceFragment(RecipeDetailsFragment())
    }

    override fun onClickRecommendationRecipe(recipe: Recipe) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipe)
        replaceFragment(recipeDetailsFragment)
    }

    override fun onClickRecipeOfWeek(recipe: Recipe) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipe)
        replaceFragment(recipeDetailsFragment)
    }

    override fun onClickHomeSeeAll(name: String) {
        Toast.makeText(requireContext(), "jh", Toast.LENGTH_SHORT).show()
        replaceFragment(SeeAllRecipesFragment())
    }

}