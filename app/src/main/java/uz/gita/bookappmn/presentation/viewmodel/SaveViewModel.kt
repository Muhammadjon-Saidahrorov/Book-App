package uz.gita.bookappmn.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.bookappmn.data.model.BookData

interface SaveViewModel {
    val loadBooks: LiveData<List<BookData>>
    val errorData: LiveData<String>
}