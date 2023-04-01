package com.example.tastyindia.ui.categorydetails

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentCategoryDetailsBinding
import com.example.tastyindia.ui.recipedetails.RecipeDetailsFragment
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.utils.replaceFragment

class CategoryDetailsFragment : BaseFragment<FragmentCategoryDetailsBinding>(),
    RecipesAdapter.RecipeInteractionListener {

    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    private lateinit var recipeAdapter: RecipesAdapter
    private lateinit var categoryName: String
    private lateinit var recipes: ArrayList<Recipe>

    override val TAG: String = this::class.simpleName.toString()
    override fun getViewBinding(): FragmentCategoryDetailsBinding =
        FragmentCategoryDetailsBinding.inflate(layoutInflater)

    override fun setUp() {


        getCategoryArgs()
        initRecyclerView()
        recipes = dataManager.getRecipesByCategory(categoryName)
        recipeAdapter.setData(recipes)
        showCategoryImage()
        binding.tvTitle.text = categoryName
        addCallBacks()
        binding.tvKitchenDetails.text =  getTheDetailsText()
    }

    private fun showCategoryImage() {
        Glide
            .with(binding.root)
            .load(recipes[0].imageUrl)
            .placeholder(R.drawable.ic_error)
            .into(binding.ivRecipe)
    }

    private fun addCallBacks() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getCategoryArgs() {
        arguments?.let {
            categoryName = it.getString(CATEGORY_NAME).toString()
        }
    }

    private fun initRecyclerView() {
        recipeAdapter = RecipesAdapter(this)
        binding.rvRecipe.adapter = recipeAdapter
    }

    override fun onClickItem(recipeId: Int) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId)
        replaceFragment(recipeDetailsFragment)
    }

    private fun getTheDetailsText() = when (categoryName) {
        "Chicken" -> "food rich in protein, chicken can help with weight" +
                " management and reduce the risk of heart disease." +
                " Chicken contains the amino acid tryptophan," +
                " which has been linked to higher levels of serotonin in our brains."
        "Meat" -> "Meats rich in protein." +
                " Red meat provides us with iron, zinc and B vitamins. Meat is one" +
                " of the main sources of vitamin B12 in the diet. Food hygiene is" +
                " important when storing, preparing and cooking meat."
        "Soup" -> "Since soups are mostly liquid, they're a great way to stay hydrated and" +
                " full. They give your immune system a boost. Soups can help you stave off" +
                " cold and flu, and they're a great antidote for times when you are sick."
        "Fish" -> "Eating fish is an important source of omega-3 fatty acids. These essential" +
                " nutrients keep our heart and brain healthy. Two omega-3 fatty acids found" +
                " in fish are EPA (eicosapentaenoic acid) and DHA (docosahexaenoic acid)."
        else -> "Spicy Foods Can Cause a “High”" +
                " Capsaicin causes pain and triggers the body to think it's in danger." +
                " In response, the body releases endorphins, which are pleasure causing" +
                " hormones, this is the body's way of trying to eliminate the “threat” ."
    }

    companion object {
        private const val CATEGORY_NAME = "categoryName"
        fun newInstance(categoryName: String) =
            CategoryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY_NAME, categoryName)
                }
            }
    }

}