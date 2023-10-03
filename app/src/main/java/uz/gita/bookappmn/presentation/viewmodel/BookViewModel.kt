package uz.gita.bookappmn.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import uz.gita.bookappmn.data.model.BookData
import java.io.File

interface BookViewModel {
    val errorData: LiveData<String>
    val downloadToast: LiveData<String>
    val check: LiveData<Boolean>

    fun download(context: Context, bookData: BookData)
}