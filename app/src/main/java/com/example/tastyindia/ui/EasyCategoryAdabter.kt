package com.example.tastyindia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tastyindia.R
import com.bumptech.glide.Glide
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.EasyCategoriesBinding
import layout.CatecoryInteractorListener

class EasyCategoryAdabter(private val easyList:List<Recipe >,private val listener:CatecoryInteractorListener) :
    RecyclerView.Adapter<EasyCategoryAdabter.EasyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EasyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.easy_categories, parent, false)
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
                listener.onClickItem(currentEasy)
            }
        }


    }


    override fun getItemCount(): Int = easyList.size

    class EasyViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = EasyCategoriesBinding.bind(viewItem)
    }
}


