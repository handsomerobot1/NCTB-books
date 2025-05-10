package com.example.nctbbooks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Original lists
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

    // Book lists for each class
    private val classBooksMap = mapOf(
        "১ম শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "২য় শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "৩য় শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "৪র্থ শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "৫ম শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "৬ষ্ঠ শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "৭ম শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "৮ম শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "৯-১০ম শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "একাদশ-দ্বাদশ শ্রেণীর বই" to listOf("বাংলা বই", "ইংরেজি বই", "গণিত বই"),
        "Class 1 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 2 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 3 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 4 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 5 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 6 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 7 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 8 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 9-10 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "Class 11-12 Books" to listOf("Bangla Book", "English Book", "Math Book"),
        "মাদ্রাসা ১ম শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "মাদ্রাসা ২য় শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "মাদ্রাসা ৩য় শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "মাদ্রাসা ৪র্থ শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "মাদ্রাসা ৫ম শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "মাদ্রাসা ৬ষ্ঠ শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "মাদ্রাসা ৭ম শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "মাদ্রাসা ৮ম শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "মাদ্রাসা ৯-১০ম শ্রেণীর বই" to listOf("আরবি বই", "ইসলাম শিক্ষা বই", "গণিত বই"),
        "প্রযুক্তিগত ৬ষ্ঠ শ্রেণীর বই" to listOf("প্রযুক্তি বই ১", "প্রযুক্তি বই ২", "প্রযুক্তি বই ৩"),
        "প্রযুক্তিগত ৭ম শ্রেণীর বই" to listOf("প্রযুক্তি বই ১", "প্রযুক্তি বই ２", "প্রযুক্তি বই ３"),
        "প্রযুক্তিগত ８ম শ্রেণীর বই" to listOf("প্রযুক্তি বই １", "প্রযুক্তি বই ２", "প্রযুক্তি বই ３"),
        "প্রযুক্তিগত ９ম শ্রেণীর বই" to listOf("প্রযুক্তি বই １", "প্রযুক্তি বই ２", "প্রযুক্তি বই ３")
    )

    private var isBangla = true
    private lateinit var currentBooks: MutableList<String>
    private lateinit var adapter: NumberAdapter
    private var showingBooks = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.numberRecyclerView)
        val primary = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.primary)
        val madrasa = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.madrasa)
        val technical = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.technical)

        recyclerView.layoutManager = LinearLayoutManager(this)
        currentBooks = primaryBooks
        adapter = NumberAdapter(this, currentBooks) { position ->
            val selectedItem = currentBooks[position]

            if (showingBooks) {
                // If already showing books, go back to class list
                showClassList()
            } else {
                // Check if the selected item has associated books
                if (classBooksMap.containsKey(selectedItem)) {
                    // Show the books for this class
                    adapter.updateItems(classBooksMap[selectedItem]!!)
                    showingBooks = true
                    Toast.makeText(this, "Showing books for $selectedItem", Toast.LENGTH_SHORT).show()
                }
            }
        }
        recyclerView.adapter = adapter

        primary.setOnClickListener {
            if (showingBooks) {
                showClassList()
            }
            toggleLanguage()
            currentBooks = if (isBangla) primaryBooks else primaryBooksEnglish
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Primary Selected", Toast.LENGTH_SHORT).show()
        }

        madrasa.setOnClickListener {
            if (showingBooks) {
                showClassList()
            }
            currentBooks = madrasaBooks
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Madrasa Selected", Toast.LENGTH_SHORT).show()
        }

        technical.setOnClickListener {
            if (showingBooks) {
                showClassList()
            }
            currentBooks = technicalBooks
            adapter.updateItems(currentBooks)
            Toast.makeText(this, "Technical Selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showClassList() {
        // Reset to show the class list based on current selection
        when (currentBooks) {
            primaryBooksEnglish -> currentBooks = primaryBooksEnglish
            madrasaBooks -> currentBooks = madrasaBooks
            technicalBooks -> currentBooks = technicalBooks
            else -> currentBooks = if (isBangla) primaryBooks else primaryBooksEnglish
        }
        adapter.updateItems(currentBooks)
        showingBooks = false
    }

    private fun toggleLanguage() {
        isBangla = !isBangla
    }
}