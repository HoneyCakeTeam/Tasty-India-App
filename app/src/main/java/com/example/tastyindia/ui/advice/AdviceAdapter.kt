package com.example.tastyindia.ui.advice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.databinding.ItemAdvicesBinding

class AdviceAdapter(val list: List<Advice>) :
    RecyclerView.Adapter<AdviceAdapter.AdviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_advices, parent, false)
        return AdviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdviceViewHolder, position: Int) {
        val currentAdvice = list[position]
        holder.binding.apply {
            adviceText.text = currentAdvice.text
            Glide
                .with(holder.binding.root)
                .load(currentAdvice.url)
                .placeholder(R.drawable.ic_error)
                .into(imgRelatedWithAdvice)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class AdviceViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemAdvicesBinding.bind(viewItem)
    }
}