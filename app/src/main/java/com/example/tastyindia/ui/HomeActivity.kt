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
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.data.source.RecipeDataSource
import com.example.tastyindia.databinding.ActivityHomeBinding
import com.example.tastyindia.ui.category.CategoryFragment
import com.example.tastyindia.ui.home.HomeFragment
import com.example.tastyindia.ui.kitchen.KitchenFragment
import com.example.tastyindia.ui.kitchen.KitchenInfoFragment
import com.example.tastyindia.ui.search.SearchFragment
import com.example.tastyindia.utils.CsvParser

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var parser: CsvParser
    private lateinit var datasource: RecipeDataSource
    private lateinit var fragmentHome: HomeFragment
    private lateinit var fragmentCategory: CategoryFragment
    private lateinit var fragmentCuisine: KitchenFragment
    private lateinit var fragmentSearch: SearchFragment
    private lateinit var fragmentKitchenInfo: KitchenInfoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObjects()
        setup()
        initSubView()
        addNavigationListener()
        statusBarMode(this)
    }

    private fun initObjects() {
        fragmentHome = HomeFragment()
        fragmentCategory = CategoryFragment()
        fragmentCuisine = KitchenFragment()
        fragmentSearch = SearchFragment()
        fragmentKitchenInfo = KitchenInfoFragment()
    }

    private fun setup() {
        parser = CsvParser()
        datasource = CsvDataSource(this, parser)
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
                else -> false
            }
        }
    }

    private fun initSubView() {
        addFragment(fragmentHome)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null)
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
                Configuration.UI_MODE_NIGHT_YES -> decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                else -> decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}