package com.example.tastyindia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R
import com.example.tastyindia.databinding.ItemHomeCategoriesBinding

class HomeCategoriesAdapter(
    val list: List<Any>,
    val listener:HomeCategoriesInteractionListener
)
    : RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_categories,parent,false)
        return HomeCategoriesViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: HomeCategoriesViewHolder, position: Int) {
        val categoriesList = list[position]
        holder.binding.apply {
            //tvItemHomeCategory.text = categoriesList.
            //imgHomeCategory.background = categoriesList.
            root.setOnClickListener{
                //listener.onClickCategory()
            }
        }
    }

    class HomeCategoriesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding = ItemHomeCategoriesBinding.bind(itemView)
    }

    interface HomeCategoriesInteractionListener{
        fun onClickCategory(categoryName:String)
    }

}