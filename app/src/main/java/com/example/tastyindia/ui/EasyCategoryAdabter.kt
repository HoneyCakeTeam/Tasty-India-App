package com.example.tastyindia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tastyindia.R
import com.bumptech.glide.Glide
import com.example.tastyindia.databinding.EasyCategoriesBinding
import com.example.tastyindia.databinding.HealthCategoriesBinding

class EasyCategoryAdabter(private val easyList: List<Pair<Pair<String, Int>, String>>) :
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
            tvName.text = currentEasy.first.first
            tvCount.text = currentEasy.first.second.toString()
            Glide
                .with(holder.binding.root)
                .load(currentEasy.second)
                .into(easyImage)
        }

    }


    override fun getItemCount(): Int = easyList.size

    class EasyViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = EasyCategoriesBinding.bind(viewItem)
    }
}


