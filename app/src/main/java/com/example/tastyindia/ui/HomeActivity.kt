package com.example.tastyindia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tastyindia.R
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.data.source.RecipeDataSource
import com.example.tastyindia.databinding.ActivityHomeBinding
import com.example.tastyindia.utils.CsvParser

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var parser: CsvParser
    private lateinit var datasource: RecipeDataSource
    private val fragmentHome = HomeFragment()
    private val fragmentCategory = CategoryFragment()
    private val fragmentKitchen = CuisineFragment()
    private val fragmentSearch = SearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        initSubView()
        addNavigationListener()

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
                R.id.kitchenFragment -> {
                    replaceFragment(fragmentKitchen)
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
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }
}