package com.example.nctbbooks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        val recyclerView = findViewById<RecyclerView>(R.id.subjectRecyclerView)

        val subjects = listOf("বাংলা", "English", "গণিত")
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OneAdapter(subjects) { subject ->

            if (subject == "বাংলা") {
                val intent = Intent(this, PdfViewerActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
