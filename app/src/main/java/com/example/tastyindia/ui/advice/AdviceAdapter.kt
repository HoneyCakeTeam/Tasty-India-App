package com.example.tastyindia.ui.advice

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.databinding.ItemAdvicesBinding
import com.example.tastyindia.ui.base.BaseAdapter
import com.example.tastyindia.ui.base.BaseInteractionListener

class AdviceAdapter(
    private val advices: List<Advice>,
    private val listener: AdvicesInteractionListener
) :
    BaseAdapter<Advice, ItemAdvicesBinding>(advices, listener) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemAdvicesBinding
        get() = ItemAdvicesBinding::inflate

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemAdvicesBinding>,
        position: Int,
        currentItem: Advice
    ) {
        holder.binding.apply {
            adviceText.text = currentItem.text
            Glide
                .with(holder.binding.root)
                .load(currentItem.url)
                .placeholder(R.drawable.ic_error)
                .into(imgVeg)
        }
    }

    interface AdvicesInteractionListener : BaseInteractionListener

}