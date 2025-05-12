package com.example.nctbbooks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class MainActivity : AppCompatActivity() {

    private val primaryBooks = mutableListOf(
        "১ম শ্রেণীর বই", "২য় শ্রেণীর বই", "৩য় শ্রেণীর বই",
        "৪র্থ শ্রেণীর বই", "৫ম শ্রেণীর বই", "৬ষ্ঠ শ্রেণীর বই",
        "৭ম শ্রেণীর বই", "৮ম শ্রেণীর বই", "৯-১০ম শ্রেণীর বই",
        "একাদশ-দ্বাদশ শ্রেণীর বই"
    )

    private val primaryBooksEnglish = mutableListOf(
        "Class 1 Books", "Class 2 Books", "Class 3 Books",
        "Class 4 Books", "Class 5 Books", "Class 6 Books",
        "Class 7 Books", "Class 8 Books", "Class 9-10 Books",
        "Class 11-12 Books"
    )

    private var isBangla = true
    private lateinit var currentBooks: MutableList<String>
    private lateinit var adapter: NumberAdapter
    private var showingBooks = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.numberRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Apply item animator for better animations
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 500
            removeDuration = 500
            moveDuration = 300
            changeDuration = 300
        }

        currentBooks = primaryBooks
        adapter = NumberAdapter(this, currentBooks) { position ->
            val selectedItem = currentBooks[position]

            if (showingBooks) {
                showClassList()
            } else {
                adapter.updateItems(listOf("Book 1", "Book 2", "Book 3")) // Replace with actual book list
                showingBooks = true
                Toast.makeText(this, "Showing books for $selectedItem", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = adapter

        // Setup drag and swipe
        // Setup drag and swipe
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                adapter.moveItem(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                adapter.deleteItem(position)  // Trigger the delete confirmation dialog
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.primary).setOnClickListener {
            if (showingBooks) showClassList()
            toggleLanguage()
            currentBooks = if (isBangla) primaryBooks else primaryBooksEnglish
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Primary Selected", Toast.LENGTH_SHORT).show()
        }

        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.madrasa).setOnClickListener {
            if (showingBooks) showClassList()
            currentBooks = mutableListOf("Madrasa 1", "Madrasa 2", "Madrasa 3")
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Madrasa Selected", Toast.LENGTH_SHORT).show()
        }

        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.technical).setOnClickListener {
            if (showingBooks) showClassList()
            currentBooks = mutableListOf("Technical 1", "Technical 2", "Technical 3")
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Technical Selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showClassList() {
        currentBooks = if (isBangla) primaryBooks else primaryBooksEnglish
        adapter.updateItems(currentBooks)
        showingBooks = false
    }

    private fun toggleLanguage() {
        isBangla = !isBangla
    }

    override fun onBackPressed() {
        if (showingBooks) {
            showClassList()
            Toast.makeText(this, "Back to class list", Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }
}
