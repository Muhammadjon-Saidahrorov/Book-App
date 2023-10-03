package uz.gita.bookappmn.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.domain.usecase.SaveUseCase
import uz.gita.bookappmn.presentation.viewmodel.SaveViewModel
import javax.inject.Inject

@HiltViewModel
class SaveViewModelImpl @Inject constructor(private val saveUseCase: SaveUseCase) : SaveViewModel,
    ViewModel() {
    override val loadBooks : LiveData<List<BookData>> = saveUseCase.getBooksSave()
    override val errorData = MutableLiveData<String>()
}