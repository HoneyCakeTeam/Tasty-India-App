package com.example.tastyindia.ui.home


import androidx.fragment.app.Fragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.HomeItem
import com.example.tastyindia.data.domain.HomeItemType
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentHomeBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.CsvParser


class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    HomeRecommendationAdapter.HomeRecommendationsListener,
    HomeRecipesOfTheWeekAdapter.RecipeOfTheWeekInteractionListener,
    HomeCategoriesAdapter.HomeCategoriesInteractionListener
{

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface

    override val TAG: String = "HomeFragment"

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        addCallbacks()
        setUpAppBar(false)

        val randomNumbersForRecommendations = dataManager.getRandomNumbersForRecommendations()
        val listOfRecommendationRecipes = dataManager.getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)

        val randomNumbersForRecipesOfWeek = dataManager.getRandomNumbersForRecipesOfTheWeek()
        val listOfRecipesOfWeek = dataManager.getListOfRecipeUsingRandomNumbers(randomNumbersForRecipesOfWeek)

        //val listOfCategories = dataManager.

        val itemList: MutableList<HomeItem<Any>> = mutableListOf()
        itemList.add(HomeItem("", HomeItemType.TYPE_HOME_WELCOME_HEADER))
        itemList.add(HomeItem(listOfRecommendationRecipes, HomeItemType.TYPE_HOME_RECOMMENDATION_RECYCLE))
        itemList.add(HomeItem(listOfRecipesOfWeek, HomeItemType.TYPR_RECIPES_OF_WEEK_RECYCLE))
        //itemList.add(HomeItem(listOfCategories,HomeItemType.TYPE_HOME_CATEGORIES))

        val adapter = HomeAdapter(itemList , this,this)
        binding.recyclevHome.adapter = adapter


        addCallbacks()
        /*
        val randomNumbersForRecommendations = dataManager.getRandomNumbersForRecommendations()
        val listOfRecommendationRecipes =
            dataManager.getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)
        val adapter = HomeRecommendationAdapter(listOfRecommendationRecipes, this)
        binding.rvHomeRecommendation.adapter = adapter

        val randomNumbers = dataManager.getRandomNumbersForRecipesOfTheWeek()
        val recipesOfTheWeek = dataManager.getListOfRecipeUsingRandomNumbers(randomNumbers)
        */
    }

    private fun addCallbacks() {
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.tastyindia.R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

    override fun onClickRecipeOfWeek(id: Int) {
        replaceFragment(RecipeDetailsFragment())
        //RecipeDetailsFragment.newInstance(id)
    }

    override fun onClickRecipe(id: Int) {
        replaceFragment(RecipeDetailsFragment())
        //RecipeDetailsFragment.newInstance(id)
    }

    override fun onClickCategory(categoryName: String) {
        replaceFragment(RecipeDetailsFragment())
        //CategoryDetailsFragment.newInstance(categoryName)
    }

}