package com.example.tastyindia.ui.category


import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.CategoryItem
import com.example.tastyindia.data.domain.enums.CategoryItemType
import com.example.tastyindia.data.domain.enums.SeeAllRecipesType
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.ui.seeall.SeeAllRecipesFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.onClickBackFromNavigation


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(), CategoryInteractionListener {

    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    private lateinit var allCategoriesAdapter: MainCategoryAdapter

    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(true, getString(R.string.categories_page_title))

        val healthyIngredients = dataManager.getHealthyIngredients()
        val listHealthy = dataManager.getHealthyRecipes(healthyIngredients)
        val listFast = dataManager.getFastFoodRecipes()
        val listEasy = dataManager.getEasyRecipes()
        val randomImageUrl = dataManager.getRandomImageInCategory()

        setUpAppBar(true, getString(R.string.categories_page_title), false)

        val itemList: MutableList<CategoryItem<Any>> = mutableListOf()
        itemList.add(CategoryItem(randomImageUrl, CategoryItemType.TYPE_POSTER_IMAGE))
        itemList.add(CategoryItem(listHealthy, CategoryItemType.TYPE_HEALTHY_CATEGORY))
        itemList.add(CategoryItem(listFast, CategoryItemType.TYPE_FAST_CATEGORY))
        itemList.add(CategoryItem(listEasy, CategoryItemType.TYPE_EASY_CATEGORY))
        allCategoriesAdapter = MainCategoryAdapter(itemList, this)
        binding.rvCategories.adapter = allCategoriesAdapter
        onClickBackFromNavigation()
    }

    override fun onClickRecipe(recipeId: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId)
        replaceFragment(recipeDetailsFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onClickSeeAll(type: SeeAllRecipesType) {
        val seeAllFragment = SeeAllRecipesFragment.newInstance(type)
        replaceFragment(seeAllFragment)
    }

}