package com.example.tastyindia.ui


import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.data.source.RecipeDataSource
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.utils.CsvParser


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(){

    private lateinit var parser: CsvParser
    private lateinit var datasource: RecipeDataSource
    override val TAG: String = "CategoryFragment"

    override fun getViewBinding(): FragmentCategoryBinding = FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        parser = CsvParser()
        datasource = CsvDataSource(requireContext(), parser)

        val recipe = listOf("Chicken","Fish","Lentils","Millet","Cardamom","Tomatoes","Ginger","Turmeric","Cinnamon","Sweet Potato","Spinach","Spinach","Fenugreek")
        val list = filterFastRecipes()
        log(list.toString())
    }

    override fun addCallbacks() {

    }

    fun filterHealthRecipes( health :List<String>):List<Recipe>{
        return datasource.getAllRecipes().filter {recipe->
            health.any {
                recipe.ingredients.contains(it,true)
            }
        }
    }
    fun filterFastRecipes():List<Int>{
        return datasource.getAllRecipes().sortedBy{it.totalTime
        }.map { it.totalTime } }

    fun filterEasyRecipes():List<Recipe>{
        return datasource.getAllRecipes().sortedBy{it.ingredientsCount
        }.take(10) }




}