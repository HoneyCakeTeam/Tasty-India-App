package com.example.tastyindia.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tastyindia.data.domain.HomeCategoriesModel
import com.example.tastyindia.databinding.ItemHomeCategoriesBinding
import com.example.tastyindia.base.BaseAdapter
import com.example.tastyindia.base.BaseInteractionListener

class HomeCategoriesAdapter(
    categories: List<HomeCategoriesModel>,
    private val listener: HomeCategoriesInteractionListener
) : BaseAdapter<HomeCategoriesModel, ItemHomeCategoriesBinding>(categories) {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemHomeCategoriesBinding
        get() = ItemHomeCategoriesBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemHomeCategoriesBinding>,
        position: Int,
        currentItem: HomeCategoriesModel
    ) {
        holder.binding.apply {
            tvItemHomeCategory.text = currentItem.categoryName
            imgHomeCategory.setImageResource(currentItem.categoryImage)
            root.setOnClickListener {
                listener.onClickCategory(currentItem.categoryName)
            }
        }
    }

    interface HomeCategoriesInteractionListener : BaseInteractionListener {
        fun onClickCategory(categoryName: String)
    }

}