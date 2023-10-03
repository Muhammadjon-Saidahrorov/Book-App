package uz.gita.bookappmn.domain.usecase.impl


import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.domain.repository.AppRepository
import uz.gita.bookappmn.domain.usecase.BookUseCase
import uz.gita.bookappmn.utils.myLog
import java.io.File
import javax.inject.Inject

class BookUseCaseImpl @Inject constructor(private val appRepository: AppRepository) : BookUseCase {
    override fun downloadFile(context: Context, name: String): Flow<Result<File>> {
        return appRepository.downloadFile(context, name)
    }
    override fun updateBook(book: BookData) {
        myLog("UPDATE")
        appRepository.updateBook(book)
    }
}