package com.example.tastyindia.ui.advice

import com.example.tastyindia.R
import com.example.tastyindia.data.DataManager
import com.example.tastyindia.data.DataManagerInterface
import com.example.tastyindia.data.source.CsvDataSource
import com.example.tastyindia.databinding.FragmentAdvicesBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.utils.CsvParser

class AdvicesFragment : BaseFragment<FragmentAdvicesBinding>() {
    private lateinit var dataSource: CsvDataSource
    private lateinit var dataManager: DataManagerInterface
    override val TAG: String = this::class.java.simpleName
    override fun getViewBinding(): FragmentAdvicesBinding =
        FragmentAdvicesBinding.inflate(layoutInflater)

    override fun setUp() {
        setUpAppBar(true , getString(R.string.advices))
        dataSource = CsvDataSource(requireContext(), CsvParser())
        dataManager = DataManager(dataSource)
        val adviceAdapter = AdviceAdapter(dataManager.getAdvicesList())
        binding.recyclerAdvices.adapter = adviceAdapter
    }

}