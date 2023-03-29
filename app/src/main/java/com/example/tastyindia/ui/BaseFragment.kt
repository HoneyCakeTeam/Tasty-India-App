package com.example.tastyindia.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.data.source.RecipeDataSource
import com.example.tastyindia.utils.CsvParser
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by Aziza Helmy on 3/18/2023.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    abstract val TAG: String
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private lateinit var parser: CsvParser
    private lateinit var datasource: RecipeDataSource
    protected val listOfRecipe by lazy {
        parser = CsvParser()
        datasource = CsvDataSource(requireContext(), parser)
        datasource.getAllRecipes()
    }

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAppBar()
        setUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    abstract fun setUp()

    protected fun log(value: Any) {
        Log.e(TAG, value.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun setUpAppBar(
        appbarVisibility: Boolean = false,
        title: String? = null,
        showBackButton: Boolean = false,
        showInfoButton: Boolean = false
    ) {
        activity?.findViewById<ConstraintLayout>(R.id.app_toolbar)?.let { toolbar ->
            toolbar.visibility = if (appbarVisibility) View.VISIBLE else View.GONE
        }

        activity?.findViewById<TextView>(R.id.text_pageTitle)?.let { pageTitle ->
            pageTitle.text = title ?: ""
        }

        activity?.findViewById<ImageButton>(R.id.button_navDirection)?.let { navigateIcon ->
            navigateIcon.visibility = if (showBackButton) View.VISIBLE else View.GONE
        }

        activity?.findViewById<ImageButton>(R.id.button_info)?.let { infoButton ->
            infoButton.visibility = if (showInfoButton) View.VISIBLE else View.GONE
        }
    }
}