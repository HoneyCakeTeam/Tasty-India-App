package com.example.tastyindia.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tastyindia.R

class InstructionsAdapter :RecyclerView.Adapter<InstructionsAdapter.InstructionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class InstructionsViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem) {
        val textInstructions : TextView = viewItem.findViewById(R.id.tv_instructions)
    }
}