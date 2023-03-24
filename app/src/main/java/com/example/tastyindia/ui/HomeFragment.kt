package com.example.tastyindia.ui


import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeRecommendationsListener {

    override val TAG: String = "HomeFragment"

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {

        setUpAppBar(false)

        val randomNumbersForRecommendations = getRandomNumbersForRecommendations()
        val listOfRecommendationRecipes = getListOfRecipeUsingRandomNumbers(randomNumbersForRecommendations)
        val adapter = HomeRecommendationAdapter(listOfRecommendationRecipes, this)
        binding?.rvHomeRecommendation?.adapter = adapter

        val randomNumbers = getRandomNumbersForRecipesOfTheWeek()
        val recipesOfTheWeek = getListOfRecipeUsingRandomNumbers(randomNumbers)
        log("setUpppp: ${randomNumbers}")
        log("setUpppp: ${recipesOfTheWeek.map { it.recipeName }}")

    }

    override fun addCallbacks() {

        binding.tvHomeRecommendationSeeAll.setOnClickListener {
            replaceFragment(SeeAllCategoriesFragment())
        }
        binding.tvHomeRecipeOfWeekSeeAll.setOnClickListener {
            replaceFragment(SeeAllCategoriesFragment())
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

    private fun getRandomNumbersForRecommendations(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(0, listOfRecipe.size - 1)
        }
        return listOfRandomNumbers
    }

    private fun getRandomNumbersForRecipesOfTheWeek(): List<Int> {
        val listOfRandomNumbers = List(10) {
            Random.nextInt(listOfRecipe.size - 1)
        }
        return listOfRandomNumbers
    }

    private fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe> =
        randomNumbers.map {
            listOfRecipe[it]
        }

    override fun onClickItem(id: Int) {
        Toast.makeText(requireContext(), "$id", Toast.LENGTH_SHORT).show()
        replaceFragment(RecipeDetailsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.tastyindia.R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

}