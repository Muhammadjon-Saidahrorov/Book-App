package uz.gita.bookappmn.domain.usecase

import androidx.lifecycle.LiveData
import uz.gita.bookappmn.data.model.BookData

interface ExploreUseCase {
    fun getBooks(): LiveData<List<BookData>>
    fun getAllBooksByExploreCategory(query: String): List<BookData>
    fun getBooksList(): List<BookData>
}