package com.example.nctbbooks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        "একাদশ-দ্বাদশ শ্রেণীর বই",
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
        "Class 11-12 Books",
    )

    private val madrasaBooks = mutableListOf(
        "মাদ্রাসা ১ম শ্রেণীর বই",
        "মাদ্রাসা ২য় শ্রেণীর বই",
        "মাদ্রাসা ২য় শ্রেণীর বই",
        "মাদ্রাসা ৩য় শ্রেণীর বই",
        "মাদ্রাসা ৪র্থ শ্রেণীর বই",
        "মাদ্রাসা ৫ম শ্রেণীর বই",
        "মাদ্রাসা ৬ষ্ঠ শ্রেণীর বই",
        "মাদ্রাসা ৭ম শ্রেণীর বই",
        "মাদ্রাসা ৮ম শ্রেণীর বই",
        "মাদ্রাসা ৯-১০ম শ্রেণীর বই"
    )

    private val technicalBooks = mutableListOf(
        "প্রযুক্তিগত ৬ষ্ঠ শ্রেণীর বই",
        "প্রযুক্তিগত ৭ম শ্রেণীর বই",
        "প্রযুক্তিগত ৮ম শ্রেণীর বই",
        "প্রযুক্তিগত ৯ম শ্রেণীর বই"
    )

    private var isBangla = true
    private lateinit var currentBooks: MutableList<String>
    private lateinit var adapter: NumberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.numberRecyclerView)
        val primary = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.primary)
        val madrasa = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.madrasa)
        val technical = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.technical)

        recyclerView.layoutManager = LinearLayoutManager(this)
        currentBooks = primaryBooks
        adapter = NumberAdapter(this, currentBooks)
        recyclerView.adapter = adapter

        primary.setOnClickListener {
            toggleLanguage()
            currentBooks = if (isBangla) primaryBooks else primaryBooksEnglish
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Primary Selected", Toast.LENGTH_SHORT).show()
        }

        madrasa.setOnClickListener {
            currentBooks = madrasaBooks
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Madrasa Selected", Toast.LENGTH_SHORT).show()
        }

        technical.setOnClickListener {
            currentBooks = technicalBooks
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Technical Selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toggleLanguage() {
        isBangla = !isBangla
    }
}