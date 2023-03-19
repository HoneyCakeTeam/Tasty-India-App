package com.example.tastyindia.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Aziza Helmy on 3/18/2023.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    abstract val TAG: String
    private var _binding: VB? = null
    protected val binding get() = _binding

    abstract fun getViewBinding():VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=getViewBinding()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        addCallbacks()
    }
    abstract fun setUp()
    abstract fun addCallbacks()
    protected fun log(value: String) {
        Log.e(TAG, value)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}