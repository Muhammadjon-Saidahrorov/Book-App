package uz.gita.bookappmn.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.bookappmn.data.model.BookData

interface HomeViewModel {
    val loadBooks: LiveData<List<BookData>>
    val errorData: LiveData<String>
    val searchedWordsLiveData:LiveData<List<BookData>>

    fun searchingNote(s:String)
    fun loadBooks()
}