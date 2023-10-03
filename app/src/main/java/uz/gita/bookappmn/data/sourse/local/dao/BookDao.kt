package uz.gita.bookappmn.data.sourse.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.data.sourse.local.entity.BookEntity

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(book: BookEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBook(book: BookEntity)

    @Query("SELECT * FROM books")
    fun getBooks(): LiveData<List<BookData>>

    @Query("SELECT * FROM books")
    fun getBooksList(): List<BookData>

    @Query("SELECT * FROM books WHERE save=1")
    fun getBooksSave(): LiveData<List<BookData>>

    @Query("SELECT * FROM books WHERE title LIKE :query")
    fun getAllBooksByHomeQuery(query: String): List<BookData>

    @Query("SELECT * FROM books WHERE genre LIKE :query")
    fun getAllBooksByExploreCategory(query: String): List<BookData>

}