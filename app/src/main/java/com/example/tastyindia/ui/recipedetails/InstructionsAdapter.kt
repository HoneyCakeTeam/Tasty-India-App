package com.example.tastyindia.ui.recipedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R

class InstructionsAdapter(private val instructionsList: List<String>) :
    RecyclerView.Adapter<InstructionsAdapter.InstructionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_instructions, parent, false)
        return InstructionsViewHolder(itemView)
    }

    override fun getItemCount(): Int = instructionsList.size

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        holder.textInstructions.text = instructionsList[position]
    }

    class InstructionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textInstructions: TextView = itemView.findViewById(R.id.tv_instructions)
    }
}
