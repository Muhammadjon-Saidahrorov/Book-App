package uz.gita.bookappmn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.github.barteksc.pdfviewer.util.FitPolicy
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappmn.data.sourse.LocalStorage
import uz.gita.bookappmn.databinding.ActivityPdfBinding
import uz.gita.bookappmn.utils.myLog
import java.io.File

@AndroidEntryPoint
class PdfActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfBinding
    private val args by navArgs<PdfActivityArgs>()
    private val localStorage = LocalStorage.getInstance()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (localStorage.getLogicRead() == false) {
            val pageNumber = localStorage.getBookPage(localStorage.getBookName()!!) as Int

            val file = localStorage.getReference()?.let { File(this.filesDir, it) }
            if (file!!.exists()) {
                myLog("bor o'qidi")
            } else myLog("fayl topilmadi")
            binding.pdfView.fromFile(file)
                .enableSwipe(true)
                .defaultPage(pageNumber)
                .swipeHorizontal(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .onPageChange { page, pagecount ->
                    localStorage.saveBookPage(localStorage.getBookName()!!, page)
                }
                .enableDoubletap(true)
                .enableAnnotationRendering(false)
                .scrollHandle(DefaultScrollHandle(this))
                .enableAntialiasing(true)
                .spacing(10)
                .nightMode(false)
                .pageFitPolicy(FitPolicy.BOTH)
                .load()
        } else {
            val bookData = args.bookData!!

            val pageNumber = localStorage.getBookPage(bookData.title) as Int

            val file = File(this.filesDir, bookData.reference)
            if (file.exists()) {
                myLog("bor o'qidi")
            } else myLog("fayl topilmadi")
            binding.pdfView.fromFile(file)
                .enableSwipe(true)
                .defaultPage(pageNumber)
                .swipeHorizontal(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .onPageChange { page, _ ->
                    localStorage.saveBookPage(bookData.title, page)
                }
                .enableDoubletap(true)
                .enableAnnotationRendering(false)
                .scrollHandle(DefaultScrollHandle(this))
                .enableAntialiasing(true)
                .spacing(10)
                .nightMode(false)
                .pageFitPolicy(FitPolicy.BOTH)
                .load()

            localStorage.saveReference(bookData.reference)
            localStorage.saveBookName(bookData.title)
            localStorage.saveBookAuthor(bookData.author)
            localStorage.saveBookImg(bookData.coverUrl)
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}