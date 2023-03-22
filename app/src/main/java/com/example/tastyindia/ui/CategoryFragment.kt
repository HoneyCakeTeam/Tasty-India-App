package com.example.tastyindia.ui


import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.FragmentCategoryBinding


class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    override val TAG: String = "CategoryFragment"

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        val recipe = listOf(
            "Chicken", "Fish", "Lentils", "Millet", "Cardamom", "Tomatoes", "Ginger", "Turmeric", "Cinnamon", "Sweet Potato", "Spinach", "Spinach", "Fenugreek"
        )
        val listHealthy = filterHealthyRecipes(recipe)
        log(listHealthy.toString())
        val listFast = filterFastRecipes()
        log(listFast.toString())
        val listEasy = filterEasyRecipes()
        log(listEasy.toString())
    }

    override fun addCallbacks() {
    }

    private fun filterHealthyRecipes(health: List<String>): List<Pair<String, String>> {
        return listOfRecipe.filter { excludeUnHealthyRecipes(it, health) }
            .map { Pair(it.recipeName, it.ingredients) }
    }

    private fun filterFastRecipes(): List<Pair<String, Int>> {
        return listOfRecipe.sortedBy {
            it.totalTimeInMins
        }.map { Pair(it.recipeName, it.totalTimeInMins) }
    }

    private fun filterEasyRecipes(): List<Pair<String, Int>> {
        return listOfRecipe.sortedBy {
            it.ingredientsCount
        }.map { Pair(it.recipeName, it.ingredientsCount) }
    }

    private fun excludeUnHealthyRecipes(recipe: Recipe, health: List<String>): Boolean {
        return health.any {
            recipe.ingredients.lowercase().contains(it.lowercase())
        }
    }
}