package com.example.tastyindia.ui.category


import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.CategoryItem
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.domain.enums.CategoryItemType
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.CsvParser


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(), CategoryInteractionListener {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var allCategoriesAdapter: MainCategoryAdapter

    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(true, "Categories")
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)

        val healthyIngredients = dataManager.getHealthyIngredients()
        val listHealthy = dataManager.getHealthyRecipes(healthyIngredients)
        val listFast = dataManager.getFastFoodRecipes()
        val listEasy = dataManager.getEasyRecipes()

        setUpAppBar(true, "Categories", false)

        val itemList: MutableList<CategoryItem<Any>> = mutableListOf()
        itemList.add(
            CategoryItem(
                getString(R.string.category_image_poster),
                CategoryItemType.TYPE_POSTER_IMAGE
            )
        )
        itemList.add(CategoryItem(listHealthy, CategoryItemType.TYPE_HEALTHY_CATEGORY))
        itemList.add(CategoryItem(listFast, CategoryItemType.TYPE_FAST_CATEGORY))
        itemList.add(CategoryItem(listEasy, CategoryItemType.TYPE_EASY_CATEGORY))
        allCategoriesAdapter = MainCategoryAdapter(itemList, this)
        binding.rvCategories.adapter = allCategoriesAdapter

    }

    override fun onClickRecipe(recipe: Recipe) {
      //  val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipe)
      //  replaceFragment(recipeDetailsFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

    override fun onClickSeeAll(type: CategoryItemType) {
        TODO("ADD TYPE IN SEE ALL FRAGMENT")
        /*val seeAllFragment = SeeAllRecipesFragment.newInstance(type)
            replaceFragment(seeAllFragment)*/
    }

}