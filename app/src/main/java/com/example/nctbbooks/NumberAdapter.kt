package com.example.nctbbooks

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

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

        // Apply slide-in animation
        holder.itemView.translationX = -holder.itemView.width.toFloat()
        holder.itemView.alpha = 0f
        holder.itemView.scaleX = 0.8f
        holder.itemView.scaleY = 0.8f
        holder.itemView.animate()
            .translationX(0f)
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(500)
            .setStartDelay(position * 50L) // Adds a stagger effect
            .start()
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<String>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition < items.size && toPosition < items.size) {
            Collections.swap(items, fromPosition, toPosition)
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    fun deleteItem(position: Int) {
        if (position < items.size) {
            val itemToDelete = items[position]  // Store the item temporarily

            // Show a confirmation dialog before deleting the item
            AlertDialog.Builder(context)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes") { dialog, which ->
                    // Remove the item from the list
                    items.removeAt(position)

                    // Notify the adapter to remove the item at the given position
                    notifyItemRemoved(position)
                    // Optionally notify the remaining items if you want to update them
                    // Uncomment below line if required
                    // notifyItemRangeChanged(position, items.size)

                    // Show a toast to inform the user the item has been deleted
                    Toast.makeText(context, "$itemToDelete deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    // Restore the item to its original position
                    items.add(position, itemToDelete)  // Insert the item back at the same position

                    // Notify the adapter to show the restored item
                    notifyItemInserted(position)

                    // Dismiss the dialog without making changes
                    dialog.dismiss()

                    // Optionally, you can show a toast to inform the user that the item was restored
                    Toast.makeText(context, "$itemToDelete restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }






    fun restoreItem(item: String, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun getItem(position: Int): String = items[position]
}
