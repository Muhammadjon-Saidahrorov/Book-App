package uz.gita.bookappmn.presentation.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.data.model.CategoryData
import uz.gita.bookappmn.data.model.TextData

interface ExploreViewModel {
    val loadBooks: LiveData<List<BookData>>
    val loadCategory: LiveData<List<TextData>>
    val errorData: LiveData<String>
    val searchedWordsLiveData: LiveData<List<BookData>>

    fun searchingNote(s: String)
}