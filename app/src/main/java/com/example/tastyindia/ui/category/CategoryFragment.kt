package com.example.tastyindia.ui.category


import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.ui.BaseFragment


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(), CatecoryInteractorListener {
    override val TAG: String = "CategoryFragment"
    private lateinit var healthAdabter: HealthCategoryAdapter
    private lateinit var fastFoodAdabter: FastFoodCategoryAdapter
    private lateinit var easyAdabter: EasyCategoryAdabter
    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        addCallbacks()
        val recipe = listOf(
            "Chicken",
            "Fish",
            "Lentils",
            "Millet",
            "Cardamom",
            "Tomatoes",
            "Ginger",
            "Turmeric",
            "Cinnamon",
            "Sweet Potato",
            "Spinach",
            "Spinach",
            "Fenugreek"
        )
        val listHealthy = filterHealthyRecipes(recipe)
        val listFast = filterFastFoodRecipes()
        val listEasy = filterEasyRecipes()

        healthAdabter = HealthCategoryAdapter(listHealthy, this)
        binding?.rvHealthCategories?.adapter = healthAdabter
        fastFoodAdabter = FastFoodCategoryAdapter(listFast, this)
        binding?.rvFastFoodCategories?.adapter = fastFoodAdabter
        easyAdabter = EasyCategoryAdabter(listEasy, this)
        binding?.rvEasyCategories?.adapter = easyAdabter
    }

    private fun addCallbacks() {
        binding?.seeAllHealth?.setOnClickListener {
            TODO("Not yet implemented")
        }
        binding?.seeAllFastFood?.setOnClickListener {
            TODO("Not yet implemented")
        }
        binding?.seeAllEasy?.setOnClickListener {
            TODO("Not yet implemented")
        }
    }

    private fun filterHealthyRecipes(health: List<String>): List<Recipe> {
        return listOfRecipe.filter { excludeUnHealthyRecipes(it, health) }
    }


    private fun filterFastFoodRecipes(): List<Recipe> {
        return listOfRecipe.sortedBy {
            it.totalTimeInMins
        }
    }

    private fun filterEasyRecipes(): List<Recipe> {
        return listOfRecipe.sortedBy {
            it.ingredientsCount
        }
    }

    private fun excludeUnHealthyRecipes(recipe: Recipe, health: List<String>): Boolean {
        return health.any {
            recipe.ingredients.lowercase().contains(it.lowercase())
        }
    }

    override fun onClickItem(recipe: Recipe) {
        TODO("Not yet implemented")
    }
}