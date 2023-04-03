package com.example.tastyindia.ui.recipedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentRecipeDetailsBinding
import com.example.tastyindia.base.BaseFragment
import com.example.tastyindia.databinding.RecipeDetailsHeaderBinding
import com.example.tastyindia.ui.home.HomeFragment
import com.example.tastyindia.utils.CsvParser

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {

    lateinit var recipeNameText: TextView
    lateinit var recipeDetailsHeaderBinding: RecipeDetailsHeaderBinding
    private val dataSource by lazy { CsvDataSource(requireContext(), CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    override val TAG = this::class.simpleName.toString()
    private var recipeId: Int = 0
    lateinit var recipe: Recipe

    override fun getViewBinding() = FragmentRecipeDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        recipeId = retrieveRecipeFromArguments()
        recipe = dataManager.getRecipe(recipeId)

        val ingredientsList = dataManager.getIngredients(recipe)
        val instructionsList = dataManager.getInstructions(recipe)
        val items = mutableListOf<RecipeDetailsAdapter.RecipeDetailsItem>()
        items.add(createHeader(recipe))
        items.add(createTitle())
        val ingredients = createIngredientsList(ingredientsList)
        items.addAll(ingredients)
        items.add(createSecondTitle())
        val instructions = createInstructionsList(instructionsList)
        items.addAll(instructions)
        initRecyclerView(items)
        navigateToHomeFragment()
        addCallBacks()
    }

    private fun initRecyclerView(items: MutableList<RecipeDetailsAdapter.RecipeDetailsItem>) {
        val adapter = RecipeDetailsAdapter(items)
        binding.rvIngredients.adapter = adapter
        binding.rvIngredients.layoutManager = LinearLayoutManager(context)
    }

    private fun addCallBacks() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun createHeader(recipe: Recipe): RecipeDetailsAdapter.RecipeDetailsItem.Header {
        return RecipeDetailsAdapter.RecipeDetailsItem.Header(
            recipeName = recipe.recipeName,
            recipeImage = recipe.imageUrl,
            minutesToCook = recipe.totalTimeInMinutes,
            cleaned = recipe.ingredientsCount
        )
    }

    private fun createTitle(): RecipeDetailsAdapter.RecipeDetailsItem.Title {
        return RecipeDetailsAdapter.RecipeDetailsItem.Title(
            getString(R.string.ingredients_header)
        )
    }

    private fun createSecondTitle(): RecipeDetailsAdapter.RecipeDetailsItem.Title {
        return RecipeDetailsAdapter.RecipeDetailsItem.Title(
            getString(R.string.how_to_prepare_title)
        )
    }

    private fun createIngredientsList(ingredientsList: List<String>):
            List<RecipeDetailsAdapter.RecipeDetailsItem.Ingredients> {
        return ingredientsList.map {
            RecipeDetailsAdapter.RecipeDetailsItem.Ingredients(it)
        }
    }

    private fun createInstructionsList(instructionsList: List<String>):
            List<RecipeDetailsAdapter.RecipeDetailsItem.Instructions> {
        return instructionsList.map {
            RecipeDetailsAdapter.RecipeDetailsItem.Instructions(it)
        }
    }

    private fun navigateToHomeFragment() {
        requireActivity().findViewById<ImageButton>(R.id.button_navDirection).setOnClickListener {
            val homeFragment = HomeFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, homeFragment)
                ?.commit()
        }
    }

    private fun retrieveRecipeFromArguments(): Int =
        arguments?.getInt(RECIPE_ID.toString())!!

    companion object {
        const val RECIPE_ID = 1
        fun newInstance(id: Int) =
            RecipeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(RECIPE_ID.toString(), id)
                }
            }

        fun calculateRecipeDifficultyLevel(numberOfMinutesToCook: Int) =
            when {
                numberOfMinutesToCook <= 20 -> "Easy"
                numberOfMinutesToCook <= 40 -> "Medium"
                else -> "Hard"
            }

        fun setRecipeName(recipeName: String, textView: TextView) {
            textView.text = recipeName
        }
        fun setCleanedIngredients(cleanedIngredients: Int, textView: TextView) {
            textView.text = cleanedIngredients.toString()
        }

        @SuppressLint("SetTextI18n")
        fun setTimeToCookRecipe(totalTimeInMins: Int, textView: TextView) {
            textView.text = "$totalTimeInMins Minutes"
        }


        fun setDifficultyLevel(totalTimeInMins: Int, textView: TextView) {
            textView.text = calculateRecipeDifficultyLevel(totalTimeInMins)
        }

        fun setRecipeImage(imageUrl: String, imageView: ImageView) {
            Glide.with(imageView.context).load(imageUrl).placeholder(R.drawable.ic_error)
                .into(imageView)
        }
    }
}