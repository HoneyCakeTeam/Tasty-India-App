package com.example.tastyindia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastyindia.data.CsvDataSource
import com.example.tastyindia.utils.CsvParser
import com.example.tastyindia.data.RecipeDataSource
import com.example.tastyindia.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var parser: CsvParser
    private lateinit var datasource: RecipeDataSource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        parser = CsvParser()
        datasource = CsvDataSource(this , parser)
    }
}