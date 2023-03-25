package com.example.tastyindia.ui.category


import android.view.View
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.utils.CsvParser


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(),
    HealthCategoryAdapter.CategoryInteractionListener,
    FastFoodCategoryAdapter.CategoryInteractionListener,
    EasyCategoryAdapter.CategoryInteractionListener, View.OnClickListener {

    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    private lateinit var healthAdapter: HealthCategoryAdapter
    private lateinit var fastFoodAdapter: FastFoodCategoryAdapter
    private lateinit var easyAdapter: EasyCategoryAdapter

    override val TAG: String = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun setUp() {
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)

        val healthyIngredients = dataManager.getHealthyIngredients()
        val listHealthy = dataManager.getHealthyRecipes(healthyIngredients)
        val listFast = dataManager.getFastFoodRecipes()
        val listEasy = dataManager.getEasyRecipes()

        healthAdapter = HealthCategoryAdapter(listHealthy, this)
        binding.rvHealthCategories.adapter = healthAdapter
        fastFoodAdapter = FastFoodCategoryAdapter(listFast, this)
        binding.rvFastFoodCategories.adapter = fastFoodAdapter
        easyAdapter = EasyCategoryAdapter(listEasy, this)
        binding.rvEasyCategories.adapter = easyAdapter
    }

    override fun onClickRecipe(recipe: Recipe) {

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.seeAllFastFood -> {}
            binding.seeAllEasy -> {}
            binding.seeAllHealth -> {}

        }
    }

}