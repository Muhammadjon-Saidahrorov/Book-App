package uz.gita.bookappmn.presentation.viewmodel.impl

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappmn.R
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.domain.usecase.BookUseCase
import uz.gita.bookappmn.presentation.viewmodel.BookViewModel
import uz.gita.bookappmn.utils.myLog
import java.io.File
import javax.inject.Inject

@HiltViewModel
class BookViewModelImpl @Inject constructor(private val bookUseCase: BookUseCase) : BookViewModel,
    ViewModel() {
    override val errorData = MutableLiveData<String>()
    override val downloadToast = MutableLiveData<String>()
    override val check = MutableLiveData<Boolean>()


    override fun download(context: Context, bookData: BookData) {
        check.value = false
        bookUseCase.downloadFile(context, bookData.reference)
            .onEach { result ->
                result.onSuccess {
                    check.value = true
                    downloadToast.value = "Download"

                    myLog(bookData.toString())
                    bookUseCase.updateBook(
                        BookData(
                            title = bookData.title,
                            author = bookData.author,
                            bookUrl = bookData.bookUrl,
                            coverUrl = bookData.coverUrl,
                            genre = bookData.genre,
                            reference = bookData.reference,
                            description = bookData.description,
                            rate = bookData.rate,
                            save = "1"
                        )
                    )

                }
                result.onFailure {
                    errorData.value = it.message.toString()
                }
            }.launchIn(viewModelScope)
    }

}

