package uz.gita.bookappmn.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.data.model.TextData
import uz.gita.bookappmn.domain.usecase.ExploreUseCase
import uz.gita.bookappmn.presentation.viewmodel.ExploreViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModelImpl @Inject constructor(private val exploreUseCase: ExploreUseCase) :
    ExploreViewModel, ViewModel() {
    override val loadBooks: LiveData<List<BookData>> = exploreUseCase.getBooks()
    override val loadCategory = MutableLiveData<List<TextData>>()
    override val errorData = MutableLiveData<String>()
    private var listString = ArrayList<TextData>()

    init {
        listString.add(TextData("All"))
        listString.add(TextData("Classic Literature"))
        listString.add(TextData("Fantasy"))
        listString.add(TextData("Psychology"))
        listString.add(TextData("Thriller"))

        loadCategory.value = listString
    }

    override val searchedWordsLiveData = MutableLiveData<List<BookData>>()

    override fun searchingNote(s: String) {
        if (s == "All") {
            searchedWordsLiveData.value = exploreUseCase.getBooksList()
        } else {
            searchedWordsLiveData.value = exploreUseCase.getAllBooksByExploreCategory(s)
        }
    }
}