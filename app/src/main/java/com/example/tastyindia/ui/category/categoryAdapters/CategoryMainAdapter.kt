package com.example.tastyindia.ui.category.categoryAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.LayoutCategoryEasyBinding
import com.example.tastyindia.databinding.LayoutCategoryFastBinding
import com.example.tastyindia.databinding.LayoutCategoryHealthyBinding
import com.example.tastyindia.databinding.LayoutPosterImageBinding
import com.example.tastyindia.ui.category.CategoryItem
import com.example.tastyindia.ui.category.CategoryItemType

@Suppress("UNCHECKED_CAST")
class CategoryMainAdapter(
    private val items: List<CategoryItem<Any>>,
    private val listener: CategoryInteractionListener
) :
    RecyclerView.Adapter<CategoryMainAdapter.BaseCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCategoryViewHolder {

        return when (viewType) {
            VIEW_TYPE_IMAGE_POSTER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_poster_image, parent, false)
                ImagePosterViewHolder(view)
            }
            VIEW_TYPE_CATEGORY_HEALTHY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_category_healthy, parent, false)
                HealthyFoodCategoryViewHolder(view)
            }
            VIEW_TYPE_CATEGORY_Fast -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_category_fast, parent, false)
                FastFoodCategoryViewHolder(view)
            }
            VIEW_TYPE_CATEGORY_Easy -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_category_easy, parent, false)
                EasyFoodCategoryViewHolder(view)
            }
            else -> {
                throw Exception("Unknown View Type")
            }
        }
    }

    override fun onBindViewHolder(holder: BaseCategoryViewHolder, position: Int) {
        when (holder) {
            is ImagePosterViewHolder -> bindImagePoster(holder, position)
            is HealthyFoodCategoryViewHolder -> bindHealthyCategory(holder, position)
            is FastFoodCategoryViewHolder -> bindFastCategory(holder, position)
            is EasyFoodCategoryViewHolder -> bindEasyCategory(holder, position)
        }
    }

    private fun bindImagePoster(holder: ImagePosterViewHolder, position: Int) {
        val imagePoster = items[position].item as String
        holder.binding.apply {
            Glide.with(root).load(imagePoster).into(topImage)
        }
    }

    private fun bindHealthyCategory(holder: HealthyFoodCategoryViewHolder, position: Int) {
        val listHealthy = items[position].item as List<Recipe>
        val adapter = HealthCategoryAdapter(
           listHealthy,
            listener
        )
        holder.binding.apply {
            rvHealthCategories.adapter = adapter
            seeAllHealth.setOnClickListener {listener.onClickSeeAll(CategoryItemType.TYPE_HEALTHY_CATEGORY)}
        }
    }

    private fun bindFastCategory(holder: FastFoodCategoryViewHolder, position: Int) {
        val listFast = items[position].item as List<Recipe>
        val adapter = FastFoodCategoryAdapter(
            listFast,
            listener
        )
        holder.binding.apply {
            rvFastFoodCategories.adapter = adapter
            seeAllFast.setOnClickListener {listener.onClickSeeAll(CategoryItemType.TYPE_FAST_CATEGORY)}
        }
    }

    private fun bindEasyCategory(holder: EasyFoodCategoryViewHolder, position: Int) {
        val listEasy = items[position].item as List<Recipe>
        val adapter = EasyCategoryAdapter(
            listEasy,
            listener
        )
        holder.binding.apply {
            rvEasyFoodCategories.adapter = adapter
            seeAllEasy.setOnClickListener {listener.onClickSeeAll(CategoryItemType.TYPE_EASY_CATEGORY)}
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            CategoryItemType.TYPE_POSTER_IMAGE -> VIEW_TYPE_IMAGE_POSTER
            CategoryItemType.TYPE_HEALTHY_CATEGORY -> VIEW_TYPE_CATEGORY_HEALTHY
            CategoryItemType.TYPE_FAST_CATEGORY -> VIEW_TYPE_CATEGORY_Fast
            CategoryItemType.TYPE_EASY_CATEGORY -> VIEW_TYPE_CATEGORY_Easy
        }
    }

    abstract class BaseCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ImagePosterViewHolder(itemView: View) : BaseCategoryViewHolder(itemView) {
        val binding = LayoutPosterImageBinding.bind(itemView)
    }

    class HealthyFoodCategoryViewHolder(itemView: View) : BaseCategoryViewHolder(itemView) {
        val binding = LayoutCategoryHealthyBinding.bind(itemView)
    }

    class FastFoodCategoryViewHolder(itemView: View) : BaseCategoryViewHolder(itemView) {
        val binding = LayoutCategoryFastBinding.bind(itemView)
    }

    class EasyFoodCategoryViewHolder(itemView: View) : BaseCategoryViewHolder(itemView) {
        val binding = LayoutCategoryEasyBinding.bind(itemView)
    }

    companion object {
        private const val VIEW_TYPE_IMAGE_POSTER = 50
        private const val VIEW_TYPE_CATEGORY_HEALTHY = 60
        private const val VIEW_TYPE_CATEGORY_Fast = 3
        private const val VIEW_TYPE_CATEGORY_Easy = 4
    }

}