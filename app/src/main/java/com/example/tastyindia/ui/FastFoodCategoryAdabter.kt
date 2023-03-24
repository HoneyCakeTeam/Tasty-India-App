package com.example.tastyindia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tastyindia.R
import com.bumptech.glide.Glide
import com.example.tastyindia.databinding.FastFoodCategoriesBinding
import com.example.tastyindia.databinding.HealthCategoriesBinding

class FastFoodCategoryAdabter(private val fastList: List<Pair<Pair<String, Int>,String>>) :
    RecyclerView.Adapter<FastFoodCategoryAdabter.FastFoodViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FastFoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fast_food_categories,parent,false)
        return FastFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FastFoodViewHolder, position: Int) {
        val currentFastFood = fastList[position]
        holder.binding.apply {
            tvName.text = currentFastFood.first.first
            tvTime.text = currentFastFood.first.second.toString()
            Glide
                .with(holder.binding.root)
                .load(currentFastFood.second)
                .into(fastFoodImage)
        }

    }


    override fun getItemCount(): Int = fastList.size

    class FastFoodViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = FastFoodCategoriesBinding.bind(viewItem)
    }
}


