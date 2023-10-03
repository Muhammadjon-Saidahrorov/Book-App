package uz.gita.bookappmn.domain.usecase.impl


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.domain.repository.AppRepository
import uz.gita.bookappmn.domain.usecase.HomeUseCase
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(private val appRepository: AppRepository) : HomeUseCase{

    override fun getBooks(): LiveData<List<BookData>> {
        return appRepository.getBooks()
    }


    override fun getAllBooksByHomeQuery(query: String): List<BookData> {
        return appRepository.getAllBooksByHomeQuery(query)
    }

    override fun getAllBooks(): Flow<Result<List<BookData>>> {
        return appRepository.getAllBooks()
    }
}