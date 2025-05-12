package com.example.nctbbooks

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(
    private val context: Context,
    private var items: MutableList<String>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberText: TextView = itemView.findViewById(R.id.numberText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.numberText.text = items[position]

        // Handle item click
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }

        // Handle long click for deletion
        holder.itemView.setOnLongClickListener {
            showDeleteConfirmationDialog(position)
            true
        }

        // Slide-in animation
        holder.itemView.translationX = -holder.itemView.width.toFloat()
        holder.itemView.animate()
            .translationX(0f)
            .setDuration(500)
            .setStartDelay(position * 100L) // Adds a stagger effect
            .start()
    }

    private fun showDeleteConfirmationDialog(position: Int) {
        AlertDialog.Builder(context)
            .setTitle("Delete Item")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Delete") { dialog, which ->
                items.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, items.size)
                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<String>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }
}
