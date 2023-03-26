package com.example.tastyindia.ui.category

import android.view.View
import android.widget.TextView
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.category.categoryAdapters.*
import com.example.tastyindia.utils.CsvParser
import com.google.android.material.snackbar.Snackbar

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(), BaseCategoryInteractionListener,
    View.OnClickListener {
    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(true, "Categories")
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)

        val healthyIngredients = dataManager.getHealthyIngredients()
        val healthyFoodList = dataManager.getHealthyRecipes(healthyIngredients)
        val fastFoodList = dataManager.getFastFoodRecipes()
        val easyFoodList = dataManager.getEasyRecipes()

        val itemList: MutableList<CategoryItem<Any>> = mutableListOf()
        itemList.add(
            CategoryItem(
                getString(R.string.category_image_poster),
                CategoryItemType.TYPE_POSTER_IMAGE
            )
        )
        itemList.add(CategoryItem(healthyFoodList, CategoryItemType.TYPE_HEALTHY_CATEGORY))
        itemList.add(CategoryItem(fastFoodList, CategoryItemType.TYPE_FAST_CATEGORY))
        itemList.add(CategoryItem(easyFoodList, CategoryItemType.TYPE_EASY_CATEGORY))

        val adapter = CategoryMainAdapter(itemList, this)
        binding.rvMain.adapter = adapter

    }

    override fun onClickRecipe(recipe: Recipe) {
        Snackbar.make(binding.root, "recipe name : ${recipe.recipeName}", Snackbar.LENGTH_LONG)
            .show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.see_all_health -> {Snackbar.make(binding.root, "health", Snackbar.LENGTH_LONG).show()}
            R.id.see_all_fast -> {Snackbar.make(binding.root, "Fast", Snackbar.LENGTH_LONG).show()}
            R.id.see_all_easy -> {Snackbar.make(binding.root, "Easy", Snackbar.LENGTH_LONG).show()}
        }
    }

}