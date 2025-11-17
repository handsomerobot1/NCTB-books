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
        "১ম শ্রেণীর বই",
        "২য় শ্রেণীর বই",
        "৩য় শ্রেণীর বই",
        "৪র্থ শ্রেণীর বই",
        "৫ম শ্রেণীর বই",
        "৬ষ্ঠ শ্রেণীর বই",
        "৭ম শ্রেণীর বই",
        "৮ম শ্রেণীর বই",
        "৯-১০ম শ্রেণীর বই",
        "একাদশ-দ্বাদশ শ্রেণীর বই"
    )

    private val primaryBooksEnglish = mutableListOf(
        "Class 1 Books",
        "Class 2 Books",
        "Class 3 Books",
        "Class 4 Books",
        "Class 5 Books",
        "Class 6 Books",
        "Class 7 Books",
        "Class 8 Books",
        "Class 9-10 Books",
        "Class 11-12 Books"
    )

    // Sample book data structure
    private val classBooks = mapOf(
        1 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই"),
        2 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই", "বিজ্ঞান বই"),
        3 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই", "বিজ্ঞান বই", "বাংলাদেশ ও বিশ্বপরিচয়"),
        4 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই", "বিজ্ঞান বই", "বাংলাদেশ ও বিশ্বপরিচয়", "ইসলাম ও নৈতিক শিক্ষা"),
        5 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই", "বিজ্ঞান বই", "বাংলাদেশ ও বিশ্বপরিচয়", "ধর্ম শিক্ষা"),
        6 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই", "বিজ্ঞান বই", "বাংলাদেশ ও বিশ্বপরিচয়", "কৃষি শিক্ষা"),
        7 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই", "বিজ্ঞান বই", "বাংলাদেশ ও বিশ্বপরিচয়", "কর্ম ও জীবনমুখী শিক্ষা"),
        8 to listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই", "বিজ্ঞান বই", "বাংলাদেশ ও বিশ্বপরিচয়", "তথ্য ও যোগাযোগ প্রযুক্তি")
    )

    private val classBooksEnglish = mapOf(
        1 to listOf("Bangla Book", "Math Book", "English Book"),
        2 to listOf("Bangla Book", "Math Book", "English Book", "Science Book"),
        3 to listOf("Bangla Book", "Math Book", "English Book", "Science Book", "Bangladesh and Global Studies"),
        4 to listOf("Bangla Book", "Math Book", "English Book", "Science Book", "Bangladesh and Global Studies", "Islam and Moral Education"),
        5 to listOf("Bangla Book", "Math Book", "English Book", "Science Book", "Bangladesh and Global Studies", "Religion Studies"),
        6 to listOf("Bangla Book", "Math Book", "English Book", "Science Book", "Bangladesh and Global Studies", "Agricultural Studies"),
        7 to listOf("Bangla Book", "Math Book", "English Book", "Science Book", "Bangladesh and Global Studies", "Work and Life Oriented Education"),
        8 to listOf("Bangla Book", "Math Book", "English Book", "Science Book", "Bangladesh and Global Studies", "Information and Communication Technology")
    )

    // PDF URLs for each book
    private val bookPdfUrls = mapOf(
        "0_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "0_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "0_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",

        "1_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "1_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "1_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "1_3" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",

        "2_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "2_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "2_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "2_3" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "2_4" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",

        "3_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "3_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "3_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "3_3" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "3_4" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "3_5" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",

        "4_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "4_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "4_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "4_3" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "4_4" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "4_5" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",

        "5_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "5_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "5_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "5_3" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "5_4" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "5_5" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",

        "6_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "6_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "6_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "6_3" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "6_4" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "6_5" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",

        "7_0" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "7_1" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "7_2" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "7_3" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "7_4" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf",
        "7_5" to "https://capniqfjwqttsstwotuh.supabase.co/storage/v1/object/public/books/ai.pdf"
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

        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 500
            removeDuration = 500
            moveDuration = 300
            changeDuration = 300
        }

        currentBooks = primaryBooks
        setupAdapter()
        recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(
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

        // ===== Primary Section (normal) =====
        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.primary).setOnClickListener {
            if (showingBooks) showClassList()
            toggleLanguage()
            currentBooks = if (isBangla) primaryBooks else primaryBooksEnglish
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Primary Selected", Toast.LENGTH_SHORT).show()
        }

        // ===== Madrasa = SOON =====
        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.madrasa).setOnClickListener {
            Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
        }

        // ===== Technical = SOON =====
        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.technical).setOnClickListener {
            Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupAdapter() {
        adapter = NumberAdapter(this, currentBooks) { position ->

            val clickedText = currentBooks[position]

            // ===== English section always SOON =====
            if (!isBangla) {
                Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
                return@NumberAdapter
            }

            // ===== 9-10 & 11-12 always SOON =====
            if (clickedText == "৯-১০ম শ্রেণীর বই" ||
                clickedText == "একাদশ-দ্বাদশ শ্রেণীর বই"
            ) {
                Toast.makeText(this, "Soon", Toast.LENGTH_SHORT).show()
                return@NumberAdapter
            }

            // ===== Normal flow for Bangla class 1–8 =====
            if (showingBooks) {
                openPdf(currentClassPosition, position)
            } else {
                showBooksForClass(position)
            }
        }
    }

    private fun showBooksForClass(classPosition: Int) {
        currentClassPosition = classPosition
        val books = if (isBangla) {
            classBooks[classPosition + 1] ?: listOf("বাংলা বই", "গণিত বই", "ইংরেজি বই")
        } else {
            classBooksEnglish[classPosition + 1] ?: listOf("Bangla Book", "Math Book", "English Book")
        }
        adapter.updateItems(books.toMutableList())
        showingBooks = true
    }

    private fun openPdf(classPosition: Int, bookPosition: Int) {
        val pdfKey = "${classPosition}_${bookPosition}"
        val pdfUrl = bookPdfUrls[pdfKey] ?: ""

        if (pdfUrl.isNotEmpty()) {
            val intent = Intent(this, PdfViewerActivity::class.java)
            intent.putExtra("pdfUrl", pdfUrl)

            val bookName = if (isBangla) {
                classBooks[classPosition + 1]?.get(bookPosition) ?: "Book"
            } else {
                classBooksEnglish[classPosition + 1]?.get(bookPosition) ?: "Book"
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
        if (showingBooks && currentClassPosition != -1) {
            showBooksForClass(currentClassPosition)
        }
    }

    override fun onBackPressed() {
        if (showingBooks) {
            showClassList()
        } else {
            super.onBackPressed()
        }
    }
}
