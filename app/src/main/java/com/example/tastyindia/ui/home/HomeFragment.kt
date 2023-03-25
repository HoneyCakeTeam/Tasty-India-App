package com.example.tastyindia.ui.home


import androidx.fragment.app.Fragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentHomeBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.category.CategoryFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.ui.seeall.SeeAllRecipesFragment
import com.example.tastyindia.utils.CsvParser
import kotlin.random.Random


class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    HomeRecommendationAdapter.HomeRecommendationsListener {

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
        val listOfRecommendationRecipes =
            dataManager.getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)
        val adapter = HomeRecommendationAdapter(listOfRecommendationRecipes, this)
        binding.rvHomeRecommendation.adapter = adapter

        val randomNumbers = dataManager.getRandomNumbersForRecipesOfTheWeek()
        val recipesOfTheWeek = dataManager.getListOfRecipeUsingRandomNumbers(randomNumbers)
    }

    private fun addCallbacks() {

        binding.tvHomeRecommendationSeeAll.setOnClickListener {
            replaceFragment(SeeAllRecipesFragment())
        }
        binding.tvHomeRecipeOfWeekSeeAll.setOnClickListener {
            replaceFragment(SeeAllRecipesFragment())
        }
        binding.cvCategoriesMeal.setOnClickListener {
            replaceFragment(CategoryFragment())
        }
        binding.cvHomeCategoriesChicken.setOnClickListener {
            replaceFragment(CategoryFragment())
        }
        binding.cvCategoriesSeaFood.setOnClickListener {
            replaceFragment(CategoryFragment())
        }
        binding.cvCategoriesSoups.setOnClickListener {
            replaceFragment(CategoryFragment())
        }
        binding.cvCategoriesSpicy.setOnClickListener {
            replaceFragment(CategoryFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.tastyindia.R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

    override fun onClickRecipe(recipe: Recipe) {
        replaceFragment(RecipeDetailsFragment())
        RecipeDetailsFragment.newInstance(recipe)
    }

}