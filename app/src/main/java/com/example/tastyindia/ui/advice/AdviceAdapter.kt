package com.example.tastyindia.ui.advice

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.base.BaseAdapter
import com.example.tastyindia.base.BaseInteractionListener
import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.databinding.ItemAdvicesBinding

class AdviceAdapter(
    advices: List<Advice>) :
    BaseAdapter<Advice, ItemAdvicesBinding>(advices) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemAdvicesBinding
        get() = ItemAdvicesBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemAdvicesBinding>,
        position: Int,
        currentItem: Advice
    ) {
        holder.binding.apply {
            adviceText.text = currentItem.adviceDescription
            Glide
                .with(holder.binding.root)
                .load(currentItem.adviceImageUrl)
                .placeholder(R.drawable.ic_loading)
                .into(imgVeg)
        }
    }

    interface AdvicesInteractionListener : BaseInteractionListener

}