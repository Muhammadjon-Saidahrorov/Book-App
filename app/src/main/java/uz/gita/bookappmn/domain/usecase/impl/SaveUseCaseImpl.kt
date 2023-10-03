package uz.gita.bookappmn.domain.usecase.impl


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.domain.repository.AppRepository
import uz.gita.bookappmn.domain.usecase.SaveUseCase
import javax.inject.Inject

class SaveUseCaseImpl @Inject constructor(private val appRepository: AppRepository) : SaveUseCase{
    override fun getBooksSave(): LiveData<List<BookData>> {
        return appRepository.getBooksSave()
    }
}