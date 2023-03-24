package com.example.tastyindia.ui


import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentCategoryBinding


class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    override val TAG: String = "CategoryFragment"
    private lateinit var healthAdabter: HealthCategoryAdabter
    private lateinit var fastFoodAdabter: FastFoodCategoryAdabter
    private lateinit var easyAdabter: EasyCategoryAdabter
    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        val recipe = listOf(
            "Chicken", "Fish", "Lentils", "Millet", "Cardamom", "Tomatoes", "Ginger", "Turmeric", "Cinnamon", "Sweet Potato", "Spinach", "Spinach", "Fenugreek"
        )
         val listHealthy = filterHealthyRecipes(recipe)
        val listFast = filterFastFoodRecipes()
        val listEasy = filterEasyRecipes()

        healthAdabter = HealthCategoryAdabter(listHealthy)
        binding?.rvHealthCategories?.adapter = healthAdabter
        fastFoodAdabter = FastFoodCategoryAdabter(listFast)
        binding?.rvFastFoodCategories?.adapter = fastFoodAdabter
        easyAdabter = EasyCategoryAdabter(listEasy)
        binding?.rvEasyCategories?.adapter = easyAdabter
    }

    override fun addCallbacks() {
    }

    private fun filterHealthyRecipes(health: List<String>): List<Pair<String, String>> {
        return listOfRecipe.filter { excludeUnHealthyRecipes(it, health) }
            .map { Pair(it.recipeName, it.imageUrl) }
    }


    private fun filterFastFoodRecipes(): List<Pair<Pair<String, Int>,String>> {
        return listOfRecipe.sortedBy {
            it.totalTimeInMins
        }.map { Pair(Pair(it.recipeName, it.totalTimeInMins),it.imageUrl) }
    }

    private fun filterEasyRecipes(): List<Pair<Pair<String, Int>,String>> {
        return listOfRecipe.sortedBy {
            it.ingredientsCount
        }.map { Pair(Pair(it.recipeName, it.ingredientsCount),it.imageUrl) }
    }

    private fun excludeUnHealthyRecipes(recipe: Recipe, health: List<String>): Boolean {
        return health.any {
            recipe.ingredients.lowercase().contains(it.lowercase())
        }
    }
}