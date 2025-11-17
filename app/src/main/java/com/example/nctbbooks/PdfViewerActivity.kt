package com.example.nctbbooks

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import kotlin.concurrent.thread

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var pdfView: PDFView
    private lateinit var loader: ProgressBar
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        pdfView = findViewById(R.id.pdfView)
        loader = findViewById(R.id.progressBar)

        val pdfUrl = intent.getStringExtra("pdfUrl")
        if (pdfUrl.isNullOrEmpty()) {
            Toast.makeText(this, "PDF URL not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        loadPdfFromUrl(pdfUrl)
    }

    private fun loadPdfFromUrl(url: String) {
        loader.visibility = View.VISIBLE

        thread {
            try {
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()

                if (!response.isSuccessful) {
                    runOnUiThread {
                        loader.visibility = View.GONE
                        Toast.makeText(this, "Failed to load PDF", Toast.LENGTH_SHORT).show()
                    }
                    return@thread
                }

                val bytes = response.body?.bytes()
                if (bytes == null) {
                    runOnUiThread {
                        loader.visibility = View.GONE
                        Toast.makeText(this, "PDF is empty", Toast.LENGTH_SHORT).show()
                    }
                    return@thread
                }

                // Save PDF to cache
                val file = File(cacheDir, "temp_book.pdf")
                file.writeBytes(bytes)

                runOnUiThread {
                    loader.visibility = View.GONE
                    pdfView.fromFile(file)
                        .enableSwipe(true)
                        .enableDoubletap(true)
                        .swipeHorizontal(false)
                        .defaultPage(0)
                        .load()
                }

            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    loader.visibility = View.GONE
                    Toast.makeText(this, "Error loading PDF: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
