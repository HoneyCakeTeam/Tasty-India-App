package com.example.tastyindia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.HomeCategoriesModel
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemHomeCategoriesBinding
import com.example.tastyindia.databinding.ItemHomeRecommendationsBinding

class HomeCategoriesAdapter(
    private val list: List<HomeCategoriesModel>,
    private val listener:HomeCategoriesInteractionListener
)
    : RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_categories,parent,false)
        return HomeCategoriesViewHolder(view)
    }



    override fun onBindViewHolder(holder: HomeCategoriesViewHolder, position: Int) {
        val currentCategory = list[position]
        holder.binding.apply {
            tvItemHomeCategory.text = currentCategory.categoryName
            imgHomeCategory.setImageResource(currentCategory.categoryImage)
            root.setOnClickListener{
                listener.onClickCategory(currentCategory.categoryName)
            }
        }
    }

    class HomeCategoriesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding = ItemHomeCategoriesBinding.bind(itemView)
    }

    override fun getItemCount() = list.size

    interface HomeCategoriesInteractionListener{
        fun onClickCategory(categoryName:String)
    }

}