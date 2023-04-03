package com.example.tastyindia.ui.category


import com.example.tastyindia.R
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.CategoryItem
import com.example.tastyindia.data.domain.enums.CategoryItemType
import com.example.tastyindia.data.domain.enums.SeeAllRecipesType
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.ui.seeall.SeeAllRecipesFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.onClickBackFromNavigation
import com.example.tastyindia.utils.replaceFragment


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(),
    MainCategoryAdapter.CategoryInteractionListener {

    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    private lateinit var allCategoriesAdapter: MainCategoryAdapter

    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(true, getString(R.string.categories_page_title), false)

        val healthyIngredients = dataManager.getHealthyIngredients()
        val listHealthy = dataManager.getHealthyRecipes(healthyIngredients).take(10)
        val listFast = dataManager.getFastFoodRecipes().take(10)
        val listEasy = dataManager.getEasyRecipes().take(10)
        val randomImageUrl = dataManager.getRandomImageInCategory()

        val itemList: MutableList<CategoryItem<Any>> = mutableListOf()
        itemList.add(CategoryItem(randomImageUrl, CategoryItemType.TYPE_POSTER_IMAGE))
        itemList.add(CategoryItem(listHealthy, CategoryItemType.TYPE_HEALTHY_CATEGORY))
        itemList.add(CategoryItem(listFast, CategoryItemType.TYPE_FAST_CATEGORY))
        itemList.add(CategoryItem(listEasy, CategoryItemType.TYPE_EASY_CATEGORY))

        initRecyclerView(itemList)
        onClickBackFromNavigation()
    }

    private fun initRecyclerView(itemList: MutableList<CategoryItem<Any>>) {
        allCategoriesAdapter = MainCategoryAdapter(itemList, this)
        binding.rvCategories.adapter = allCategoriesAdapter
    }

    override fun onClickRecipe(recipeId: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId)
        replaceFragment(recipeDetailsFragment)
    }

    override fun onClickSeeAll(type: SeeAllRecipesType) {
        val seeAllFragment = SeeAllRecipesFragment.newInstance(type)
        replaceFragment(seeAllFragment)
    }

}