package com.example.tastyindia.ui.category


import androidx.core.content.ContextCompat
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.category.categoryAdapters.*
import com.example.tastyindia.utils.CsvParser


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(),BaseCategoryInteractionListener {
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
            CategoryItem(ContextCompat.getDrawable(requireContext(), R.drawable.image_recipe).toString()
                , CategoryItemType.TYPE_POSTER_IMAGE))
        itemList.add(CategoryItem(healthyFoodList, CategoryItemType.TYPE_HEALTHY_CATEGORY))
        itemList.add(CategoryItem(fastFoodList, CategoryItemType.TYPE_FAST_CATEGORY))
        itemList.add(CategoryItem(easyFoodList, CategoryItemType.TYPE_EASY_CATEGORY))

        val adapter = CategoryMainAdapter(itemList, this)
        binding.rvMain.adapter = adapter
    }

    override fun onClickRecipe(recipe: Recipe) {

    }

//    override fun onClick(view: View?) {
//        when (view) {
//            binding.seeAllFastFood -> {}
//            binding.seeAllEasy -> {}
//            binding.seeAllHealth -> {}
//
//        }
//    }

}