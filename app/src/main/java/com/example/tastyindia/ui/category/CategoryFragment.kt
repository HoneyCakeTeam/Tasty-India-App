package com.example.tastyindia.ui.category


import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.ui.BaseFragment


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(),
    HealthCategoryAdapter.CategoryInteractionListener,
    FastFoodCategoryAdapter.CategoryInteractionListener,
    EasyCategoryAdapter.CategoryInteractionListener {
    override val TAG: String = "CategoryFragment"
    private lateinit var healthAdapter: HealthCategoryAdapter
    private lateinit var fastFoodAdapter: FastFoodCategoryAdapter
    private lateinit var easyAdapter: EasyCategoryAdapter
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

        healthAdapter = HealthCategoryAdapter(listHealthy, this)
        binding.rvHealthCategories.adapter = healthAdapter
        fastFoodAdapter = FastFoodCategoryAdapter(listFast, this)
        binding.rvFastFoodCategories.adapter = fastFoodAdapter
        easyAdapter = EasyCategoryAdapter(listEasy, this)
        binding.rvEasyCategories.adapter = easyAdapter
    }

    private fun addCallbacks() {
        binding.seeAllHealth.setOnClickListener {
            TODO("Not yet implemented")
        }
        binding.seeAllFastFood.setOnClickListener {
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

    override fun onClickRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }


}