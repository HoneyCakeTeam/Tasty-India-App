package com.example.tastyindia.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.CategoryItem
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.domain.enums.CategoryItemType
import com.example.tastyindia.databinding.ItemChildCategoryBinding
import com.example.tastyindia.databinding.ItemImageBinding

class AllCategoriesAdapter(
    private val categoriesList: List<CategoryItem>,
    public val listener: CategoryInteractionListener
) :
    RecyclerView.Adapter<AllCategoriesAdapter.BaseViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        return when (viewType) {
            IMAGE_CATEGORY_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
                BaseViewHolder.HeaderViewHolder(view)
            }
            HEALTH_CATEGORY_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_child_category, parent, false)
                BaseViewHolder.HealthViewHolder(view)
            }
            Fast_CATEGORY_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_child_category, parent, false)
                BaseViewHolder.FastViewHolder(view)
            }
            else -> {
                throw Exception("Unknown view")
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is BaseViewHolder.HeaderViewHolder -> bindImage(holder, position)
            is BaseViewHolder.HealthViewHolder -> bindHealthCategory(holder, position)
        }
    }

    private fun bindHealthCategory(holder: BaseViewHolder.HealthViewHolder, position: Int) {
        val currentItem = categoriesList[position].list
        val currentType = categoriesList[position].type
        if (currentType == CategoryItemType.TYPE_HEALTH_ITEM) {
            val adapter = HealthCategoryAdapter(currentItem, listener)
            holder.binding.apply {
                rvHealthCategories.adapter = adapter
            } }
        if (currentType == CategoryItemType.TYPE_FAST_ITEM) {
            val adapter = FastFoodCategoryAdapter(currentItem, listener)
            holder.binding.apply {
                tvHealth.text ="Fast"
                rvHealthCategories.adapter = adapter
            }}
            if (currentType == CategoryItemType.TYPE_EASY_ITEM) {
                val adapter = EasyCategoryAdapter(currentItem, listener)
                holder.binding.apply {
                    tvHealth.text ="Easy"
                    rvHealthCategories.adapter = adapter
                }
        }
    }

    private fun bindImage(holder: BaseViewHolder.HeaderViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return IMAGE_CATEGORY_TYPE
        }
        //else if (categoriesList[position].type==CategoryItemType.TYPE_FAST_ITEM){return Fast_CATEGORY_TYPE}
        else return HEALTH_CATEGORY_TYPE
    }


    override fun getItemCount(): Int = categoriesList.size
    abstract class BaseViewHolder(viewItem: View) : ViewHolder(viewItem) {

        class HealthViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
            val binding = ItemChildCategoryBinding.bind(viewItem)
        }

        class FastViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
            val binding = ItemChildCategoryBinding.bind(viewItem)
        }

        class HeaderViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
            val binding = ItemImageBinding.bind(viewItem)
        }
    }

    interface CategoryInteractionListener : HealthCategoryAdapter.CategoryInteractionListener,
        FastFoodCategoryAdapter.CategoryInteractionListener,
        EasyCategoryAdapter.CategoryInteractionListener {
        override fun onClickRecipe(recipe: Recipe)
    }

    companion object {
        val IMAGE_CATEGORY_TYPE = 1
        val HEALTH_CATEGORY_TYPE = 2
        val Fast_CATEGORY_TYPE = 3
        val Easy_CATEGORY_TYPE = 4


    }
}


