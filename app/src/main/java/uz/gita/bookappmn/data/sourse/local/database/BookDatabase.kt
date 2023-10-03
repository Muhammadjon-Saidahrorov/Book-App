package uz.gita.bookappmn.data.sourse.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.bookappmn.data.sourse.local.dao.BookDao
import uz.gita.bookappmn.data.sourse.local.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {
        private lateinit var database: BookDatabase
        private const val NAME_DATABASE = "book_list.db"

        fun init(context: Context) {
            if (!(Companion::database.isInitialized)) {
                database = Room.databaseBuilder(context, BookDatabase::class.java, NAME_DATABASE)
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstance() = database
    }
}