package com.example.tastyindia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.databinding.ItemAdvicesBinding

class adviceAdabter(val list: List<Advice>) : RecyclerView.Adapter<adviceAdabter.adviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_advices, parent, false)
        return adviceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: adviceViewHolder, position: Int) {
        val currentAdvice = list[position]
        holder.binding.apply {
            adviceText.text = currentAdvice.text
            Glide
                .with(holder.binding.root)
                .load(currentAdvice.url)
                .placeholder(R.drawable.ic_error)
                .into(imgVeg)
        }
    }

    class adviceViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemAdvicesBinding.bind(viewItem)
    }
}