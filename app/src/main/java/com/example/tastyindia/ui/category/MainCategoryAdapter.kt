package com.example.tastyindia.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.CategoryItem
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.domain.enums.CategoryItemType
import com.example.tastyindia.databinding.LayoutEasyCategoryBinding
import com.example.tastyindia.databinding.LayoutFastFoodCategoryBinding
import com.example.tastyindia.databinding.LayoutHealthCategoryBinding
import com.example.tastyindia.databinding.LayoutImagePosterBinding

class MainCategoryAdapter(
    private val categoriesList: List<CategoryItem<Any>>,
    private val listener: CategoryInteractionListener
) :
    RecyclerView.Adapter<MainCategoryAdapter.BaseCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCategoryViewHolder {

        return when (viewType) {
            VIEW_TYPE_IMAGE_POSTER -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_image_poster, parent, false)
                ImagePosterViewHolder(view)
            }
            VIEW_TYPE_CATEGORY_HEALTHY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_health_category, parent, false)
                HealthyCategoryViewHolder(view)
            }
            VIEW_TYPE_CATEGORY_FAST -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_fast_food_category, parent, false)
                FastFoodCategoryViewHolder(view)
            }
            VIEW_TYPE_CATEGORY_EASY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_easy_category, parent, false)
                EasyCategoryViewHolder(view)
            }
            else -> {
                throw Exception("Unknown view")
            }
        }
    }

    override fun onBindViewHolder(holder: BaseCategoryViewHolder, position: Int) {
        when (holder) {
            is ImagePosterViewHolder -> bindImagePoster(holder, position)
            is HealthyCategoryViewHolder -> bindHealthyCategory(holder, position)
            is FastFoodCategoryViewHolder -> bindFastCategory(holder, position)
            is EasyCategoryViewHolder -> bindEasyCategory(holder, position)
        }
    }

    private fun bindHealthyCategory(holder: HealthyCategoryViewHolder, position: Int) {
        val listHealthy = categoriesList[position].listItem as List<Recipe>
        val adapter = HealthCategoryAdapter(
            listHealthy,
            listener
        )
        holder.binding.apply {
            rvHealthCategories.adapter = adapter
            seeAllHealth.setOnClickListener { listener.onClickSeeAll(CategoryItemType.TYPE_HEALTHY_CATEGORY) }
        }
    }

    private fun bindFastCategory(holder: FastFoodCategoryViewHolder, position: Int) {
        val listFast = categoriesList[position].listItem as List<Recipe>
        val adapter = FastFoodCategoryAdapter(
            listFast,
            listener
        )
        holder.binding.apply {
            rvFastFoodCategories.adapter = adapter
            seeAllFastFood.setOnClickListener { listener.onClickSeeAll(CategoryItemType.TYPE_FAST_CATEGORY) }
        }
    }

    private fun bindEasyCategory(holder: EasyCategoryViewHolder, position: Int) {
        val listEasy = categoriesList[position].listItem as List<Recipe>
        val adapter = EasyCategoryAdapter(
            listEasy,
            listener
        )
        holder.binding.apply {
            rvEasyCategories.adapter = adapter
            seeAllEasy.setOnClickListener { listener.onClickSeeAll(CategoryItemType.TYPE_EASY_CATEGORY) }
        }
    }

    private fun bindImagePoster(holder: ImagePosterViewHolder, position: Int) {
        val imagePoster = categoriesList[position].listItem as String
        holder.binding.apply {
            Glide.with(root).load(imagePoster).into(topImage)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (categoriesList[position].type) {
            CategoryItemType.TYPE_POSTER_IMAGE -> VIEW_TYPE_IMAGE_POSTER
            CategoryItemType.TYPE_HEALTHY_CATEGORY -> VIEW_TYPE_CATEGORY_HEALTHY
            CategoryItemType.TYPE_FAST_CATEGORY -> VIEW_TYPE_CATEGORY_FAST
            CategoryItemType.TYPE_EASY_CATEGORY -> VIEW_TYPE_CATEGORY_EASY
        }
    }


    override fun getItemCount(): Int = categoriesList.size
    abstract class BaseCategoryViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)
    class HealthyCategoryViewHolder(viewItem: View) : BaseCategoryViewHolder(viewItem) {
        val binding = LayoutHealthCategoryBinding.bind(viewItem)
    }

    class FastFoodCategoryViewHolder(viewItem: View) : BaseCategoryViewHolder(viewItem) {
        val binding = LayoutFastFoodCategoryBinding.bind(viewItem)
    }

    class EasyCategoryViewHolder(itemView: View) : BaseCategoryViewHolder(itemView) {
        val binding = LayoutEasyCategoryBinding.bind(itemView)
    }

    class ImagePosterViewHolder(viewItem: View) : BaseCategoryViewHolder(viewItem) {
        val binding = LayoutImagePosterBinding.bind(viewItem)
    }


    companion object {
        private const val VIEW_TYPE_IMAGE_POSTER = 1
        private const val VIEW_TYPE_CATEGORY_HEALTHY = 2
        private const val VIEW_TYPE_CATEGORY_FAST = 3
        private const val VIEW_TYPE_CATEGORY_EASY = 4
    }
}


