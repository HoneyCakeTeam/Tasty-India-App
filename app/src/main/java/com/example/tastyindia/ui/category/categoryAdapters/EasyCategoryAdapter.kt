package com.example.tastyindia.ui.category.categoryAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tastyindia.R
import com.bumptech.glide.Glide
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemEasyCategoryBinding

class EasyCategoryAdapter(private val easyList:List<Recipe >, private val listener: BaseCategoryInteractionListener) :
    RecyclerView.Adapter<EasyCategoryAdapter.EasyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EasyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_easy_category, parent, false)
        return EasyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EasyViewHolder, position: Int) {
        val currentEasy = easyList[position]
        holder.binding.apply {
            tvName.text = currentEasy.recipeName
            tvCount.text = currentEasy.ingredientsCount.toString()
            Glide
                .with(holder.binding.root)
                .load(currentEasy.imageUrl)
                .into(easyImage)

            root.setOnClickListener {
                listener.onClickRecipe(currentEasy)
            }
        }

    }

    override fun getItemCount(): Int = easyList.size

    class EasyViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = ItemEasyCategoryBinding.bind(viewItem)
    }

}


