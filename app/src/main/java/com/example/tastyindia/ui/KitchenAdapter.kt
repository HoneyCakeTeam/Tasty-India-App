package com.example.tastyindia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.databinding.ItemKitchenBinding

class KitchenAdapter(private val kitchenList: List<Recipe>) :
    RecyclerView.Adapter<KitchenAdapter.KitchenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitchenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kitchen, parent, false)
        return KitchenViewHolder(view)
    }

    override fun onBindViewHolder(holder: KitchenViewHolder, position: Int) {
        val currentKitchen = kitchenList[position]

        holder.binding.apply {
            textKitchenName.text = currentKitchen.cuisine
            Glide
                .with(holder.binding.root)
                .load(currentKitchen.imageUrl)
                .placeholder(R.drawable.ic_error)
                .into(imageKitchen)
        }
    }

    override fun getItemCount() = kitchenList.size

    class KitchenViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = ItemKitchenBinding.bind(itemView)
    }
}

