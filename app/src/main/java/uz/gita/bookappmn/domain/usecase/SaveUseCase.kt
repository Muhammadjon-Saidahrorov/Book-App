package uz.gita.bookappmn.domain.usecase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappmn.data.model.BookData

interface SaveUseCase {
    fun getBooksSave(): LiveData<List<BookData>>
}