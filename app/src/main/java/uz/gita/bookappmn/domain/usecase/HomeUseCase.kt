package uz.gita.bookappmn.domain.usecase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappmn.data.model.BookData

interface HomeUseCase {
    fun getBooks(): LiveData<List<BookData>>
    fun getAllBooksByHomeQuery(query: String): List<BookData>
    fun getAllBooks(): Flow<Result<List<BookData>>>
}