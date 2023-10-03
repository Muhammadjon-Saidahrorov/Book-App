package uz.gita.bookappmn.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.data.model.CategoryData
import uz.gita.bookappmn.data.sourse.local.entity.BookEntity
import java.io.File

interface AppRepository {

    fun downloadFile(context: Context, name: String): Flow<Result<File>>

    fun getAllBooks(): Flow<Result<List<BookData>>>
    fun getAllCategorys(): Flow<Result<List<String>>>
    fun getBooksByCategory(categoryName: String): Flow<Result<List<BookData>>>
    fun getAllBooksByRate(): Flow<Result<List<BookData>>>
    fun getSaveBooks(): Flow<Result<List<BookData>>>

    fun getBooks(): LiveData<List<BookData>>
    fun getBooksSave(): LiveData<List<BookData>>
    fun getBooksList(): List<BookData>
    fun getAllBooksByHomeQuery(query: String): List<BookData>
    fun getAllBooksByExporeCategory(query: String): List<BookData>
    fun updateBook(book: BookData)

}