package uz.gita.bookappmn.domain.usecase

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappmn.data.model.BookData
import java.io.File

interface BookUseCase {
    fun downloadFile(context: Context, name: String): Flow<Result<File>>
    fun updateBook(book: BookData)
}