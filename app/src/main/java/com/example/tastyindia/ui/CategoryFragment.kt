package com.example.tastyindia.ui


import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.data.source.RecipeDataSource
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.utils.CsvParser


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(){
    override val TAG: String = "CategoryFragment"

    override fun getViewBinding(): FragmentCategoryBinding = FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        val recipe = listOf("Chicken","Fish","Lentils","Millet","Cardamom","Tomatoes","Ginger","Turmeric","Cinnamon","Sweet Potato","Spinach","Spinach","Fenugreek")
        val list = filterHealthyRecipes(recipe)
        log(list.toString())
    }

    override fun addCallbacks() {
    }

    fun filterHealthyRecipes( health :List<String>):List<String>{
        return listOfRecipe.filter {excludeUnHealthyRecipes(it,health) }
            .map { it .ingredients }
    }
    fun filterFastRecipes():List<Int>{
        return listOfRecipe.sortedBy{it.totalTime
                       }.map { it.totalTime } }

    fun filterEasyRecipes():List<Int>{
        return listOfRecipe.sortedBy{it.ingredientsCount
                      }.map { it.ingredientsCount }}

     fun excludeUnHealthyRecipes(recipe:Recipe,health: List<String>):Boolean{
        return health.any {
            recipe.ingredients.lowercase().contains(it.lowercase())
        }}

     }


