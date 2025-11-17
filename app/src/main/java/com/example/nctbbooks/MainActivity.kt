package com.example.nctbbooks

import android.content.Intent
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

    // Sample book data structure
    private val classBooks = mapOf(
        0 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই"), // Class 1 books
        1 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই"), // Class 2 books
        // Add more classes as needed
    )

    private val classBooksEnglish = mapOf(
        0 to listOf("Bangla Book", "Math Book", "English Book"),
        1 to listOf("Bangla Book", "Math Book", "English Book"),
        // Add more classes as needed
    )

    // PDF URLs for each book
    private val bookPdfUrls = mapOf(
        // Class 1 books
        "0_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "0_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "0_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",

        // Class 2 books
        "1_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "1_1" to "https://YOUR-PROJECT.supabase.co/storage/v1/object/public/books/TwoMathB.pdf",
        "1_2" to "https://YOUR-PROJECT.supabase.co/storage/v1/object/public/books/TwoEnglishB.pdf",
    )

    private var isBangla = true
    private var currentClassPosition = -1
    private lateinit var currentBooks: MutableList<String>
    private lateinit var adapter: NumberAdapter
    private lateinit var recyclerView: RecyclerView
    private var showingBooks = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.numberRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Apply item animator for better animations
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 500
            removeDuration = 500
            moveDuration = 300
            changeDuration = 300
        }

        currentBooks = primaryBooks
        setupAdapter()
        recyclerView.adapter = adapter

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
                adapter.deleteItem(position)
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

    private fun setupAdapter() {
        adapter = NumberAdapter(this, currentBooks) { position ->
            if (showingBooks) {
                // Book item clicked - open PDF
                openPdf(currentClassPosition, position)
            } else {
                // Class item clicked - show books
                showBooksForClass(position)
            }
        }
    }

    private fun showBooksForClass(classPosition: Int) {
        currentClassPosition = classPosition
        val books = if (isBangla) {
            classBooks[classPosition] ?: listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই")
        } else {
            classBooksEnglish[classPosition] ?: listOf("Bangla Book", "Math Book", "English Book")
        }

        adapter.updateItems(books.toMutableList())
        showingBooks = true

        val className = if (isBangla) primaryBooks[classPosition] else primaryBooksEnglish[classPosition]
        Toast.makeText(this, "Showing books for $className", Toast.LENGTH_SHORT).show()
    }

    private fun openPdf(classPosition: Int, bookPosition: Int) {
        val pdfKey = "${classPosition}_${bookPosition}"
        val pdfUrl = bookPdfUrls[pdfKey] ?: ""

        if (pdfUrl.isNotEmpty()) {
            val intent = Intent(this, PdfViewerActivity::class.java)
            intent.putExtra("pdfUrl", pdfUrl)

            // Get book name for title
            val bookName = if (isBangla) {
                classBooks[classPosition]?.get(bookPosition) ?: "Book"
            } else {
                classBooksEnglish[classPosition]?.get(bookPosition) ?: "Book"
            }
            intent.putExtra("bookName", bookName)

            startActivity(intent)
        } else {
            Toast.makeText(this, "PDF not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showClassList() {
        currentBooks = if (isBangla) primaryBooks else primaryBooksEnglish
        adapter.updateItems(currentBooks)
        showingBooks = false
        currentClassPosition = -1
    }

    private fun toggleLanguage() {
        isBangla = !isBangla
        // If we're showing books, update the book list in current language
        if (showingBooks && currentClassPosition != -1) {
            showBooksForClass(currentClassPosition)
        }
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