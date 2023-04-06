package com.example.tastyindia.ui

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.ActivityHomeBinding
import com.example.tastyindia.ui.advice.AdvicesFragment
import com.example.tastyindia.ui.category.CategoryFragment
import com.example.tastyindia.ui.home.HomeFragment
import com.example.tastyindia.ui.kitchen.view.KitchenFragment
import com.example.tastyindia.ui.search.SearchFragment
import com.example.tastyindia.utils.CsvParser

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val dataSource by lazy { CsvDataSource(this, CsvParser()) }
    private val dataManager: DataManagerInterface by lazy { DataManager(dataSource) }
    private val fragmentHome = HomeFragment()
    private val fragmentCategory = CategoryFragment()
    private val fragmentCuisine = KitchenFragment()
    private val fragmentSearch = SearchFragment()
    private val fragmentAdvices = AdvicesFragment()
    val recommendationFirstRecipeId: Int by lazy {
        dataManager.getRecommendationFirstRecipeId()
    }
    val recipesOfWeekFirstRecipeId: Int by lazy {
        dataManager.getRecipesOfWeekFirstRecipeId()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSubView()
        addNavigationListener()
        statusBarMode(this)
    }
    private fun addNavigationListener() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    replaceFragment(fragmentHome)
                    true
                }
                R.id.categoryFragment -> {
                    replaceFragment(fragmentCategory)
                    true
                }
                R.id.CuisineFragment -> {
                    replaceFragment(fragmentCuisine)
                    true
                }
                R.id.searchFragment -> {
                    replaceFragment(fragmentSearch)
                    true
                }
                R.id.advicesFragment -> {
                    replaceFragment(fragmentAdvices)
                    true
                }
                else -> false
            }
        }
    }

    private fun initSubView() {
        addFragment(fragmentHome)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

    private fun statusBarMode(activity: Activity) {
        activity.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    binding.appToolbar.buttonNavDirection.setColorFilter(Color.WHITE)
                }
                else -> decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}