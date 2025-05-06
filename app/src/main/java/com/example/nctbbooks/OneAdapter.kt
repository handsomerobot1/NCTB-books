package com.example.nctbbooks

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater


class OneAdapter(
    private val subjects: List<String>,
    private val onItemClick: (String) -> Unit // Click listener
) : RecyclerView.Adapter<OneAdapter.SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjects[position]
        holder.subjectTextView.text = subject
        holder.itemView.setOnClickListener {
            onItemClick(subject) // Trigger the click listener
        }
    }

    override fun getItemCount(): Int = subjects.size

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectTextView: TextView = itemView.findViewById(android.R.id.text1)
    }
}
