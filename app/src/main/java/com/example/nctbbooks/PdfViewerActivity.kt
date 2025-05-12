package com.example.nctbbooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView

class PdfViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        val pdfView = findViewById<PDFView>(R.id.pdfView)

        // Load Bangla book from assets
        pdfView.fromAsset("One Bangla B.pdf")
        pdfView.fromAsset("One Math B.pdf")
        pdfView.fromAsset("One English B.pdf") // Make sure the PDF file is located in 'assets'
            .enableSwipe(true) // Enable swipe navigation
            .swipeHorizontal(false) // Vertical swipe
            .enableDoubletap(true) // Allow double-tap to zoom
            .load()
    }
}
