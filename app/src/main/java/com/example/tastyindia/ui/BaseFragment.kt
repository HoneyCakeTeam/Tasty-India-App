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

/**
 * Created by Aziza Helmy on 3/18/2023.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    abstract val TAG: String
    private var _binding: VB? = null
    protected val binding get() = _binding
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
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        addCallbacks()
    }

    abstract fun setUp()
    abstract fun addCallbacks()
    protected fun log(value: Any?) {
        Log.e(TAG, value.toString())
    }

    private fun getAllRecipes(): List<Recipe> {
        parser = CsvParser()
        datasource = CsvDataSource(requireContext(), parser)
        return datasource.getAllRecipes()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun setAppBar(
        visibility: Boolean,
        title: String? = null,
        showNavigationIcon: Boolean = true
    ) {
        val toolbar: ConstraintLayout? = activity?.findViewById(R.id.toolbarContainer)
        val pageTitle: TextView? = activity?.findViewById(R.id.text_pageTitle)
        val navigateIcon: ImageButton? = activity?.findViewById(R.id.button_navDirection)

        if (visibility) {
            toolbar?.visibility = View.VISIBLE
            title?.let {
                pageTitle?.text = title
            }
            if (showNavigationIcon) {
                navigateIcon?.visibility = View.VISIBLE
            } else {
                navigateIcon?.visibility = View.GONE
            }
        } else {
            toolbar?.visibility = View.GONE
        }
    }
}