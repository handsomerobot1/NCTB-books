package com.example.nctbbooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.numberRecyclerView)

        val classBooks = listOf(
            "১ম শ্রেণীর বই",
            "২য় শ্রেণীর বই",
            "৩য় শ্রেণীর বই",
            "৪র্থ শ্রেণীর বই",
            "৫ম শ্রেণীর বই",
            "৬ষ্ঠ শ্রেণীর বই",
            "৭ম শ্রেণীর বই",
            "৮ম শ্রেণীর বই",
            "৯ম শ্রেণীর বই",
            "১০ম শ্রেণীর বই",
            "একাদশ শ্রেণীর বই",
            "দ্বাদশ শ্রেণির বই"
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NumberAdapter(classBooks)
    }
}
