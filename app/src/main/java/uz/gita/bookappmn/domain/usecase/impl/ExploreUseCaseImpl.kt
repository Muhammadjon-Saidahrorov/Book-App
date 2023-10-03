package uz.gita.bookappmn.domain.usecase.impl


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.domain.repository.AppRepository
import uz.gita.bookappmn.domain.usecase.ExploreUseCase
import javax.inject.Inject

class ExploreUseCaseImpl @Inject constructor(private val appRepository: AppRepository)  : ExploreUseCase {

    override fun getBooks(): LiveData<List<BookData>> {
        return appRepository.getBooks()
    }

    override fun getAllBooksByExploreCategory(query: String): List<BookData> {
        return appRepository.getAllBooksByExporeCategory(query)
    }

    override fun getBooksList(): List<BookData> {
        return appRepository.getBooksList()
    }
}