package com.example.tastyindia.ui.home


import androidx.fragment.app.Fragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.HomeItem
import com.example.tastyindia.data.domain.enums.HomeItemType
import com.example.tastyindia.data.domain.enums.SeeAllRecipesType
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentHomeBinding
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.ui.HomeActivity
import com.example.tastyindia.ui.categorydetails.CategoryDetailsFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.ui.seeall.SeeAllRecipesFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.onClickBackFromNavigation

class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    HomeRecommendationAdapter.HomeRecommendationsListener,
    HomeRecipesOfTheWeekAdapter.RecipeOfTheWeekInteractionListener,
    HomeCategoriesAdapter.HomeCategoriesInteractionListener,
    HomeAdapter.HomeSeeAllListener {

    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }

    override val TAG: String = "HomeFragment"

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        addCallbacks()
        setUpAppBar(false)

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
        binding.recyclevHome.adapter = adapter

    }

    private fun addCallbacks() {
        onClickBackFromNavigation()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.tastyindia.R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
        fragmentTransaction.commit()
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

}