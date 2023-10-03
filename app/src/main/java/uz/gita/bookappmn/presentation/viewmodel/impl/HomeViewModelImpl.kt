package uz.gita.bookappmn.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.domain.usecase.HomeUseCase
import uz.gita.bookappmn.presentation.viewmodel.HomeViewModel
import uz.gita.bookappmn.utils.myLog
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(private val homeUseCase: HomeUseCase) : HomeViewModel,
    ViewModel() {

    override val loadBooks: LiveData<List<BookData>> = homeUseCase.getBooks()
    override val errorData = MutableLiveData<String>()
    override val searchedWordsLiveData = MutableLiveData<List<BookData>>()

    override fun searchingNote(s: String) {
        searchedWordsLiveData.value = homeUseCase.getAllBooksByHomeQuery(s)
    }

    override fun loadBooks() {
        homeUseCase.getAllBooks().onEach {  }.launchIn(viewModelScope)
    }
}