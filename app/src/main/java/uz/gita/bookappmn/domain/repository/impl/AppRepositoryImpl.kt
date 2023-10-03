package uz.gita.bookappmn.domain.repository.impl

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.bookappmn.app.App
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.data.sourse.LocalStorage
import uz.gita.bookappmn.data.sourse.local.dao.BookDao
import uz.gita.bookappmn.data.sourse.local.database.BookDatabase
import uz.gita.bookappmn.domain.repository.AppRepository
import uz.gita.bookappmn.utils.myLog
import java.io.File
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val storage = Firebase.storage
    private val db = Firebase.firestore
    private val dao: BookDao = BookDatabase.getInstance().getBookDao()
    private val localStorage = LocalStorage.getInstance()

    override fun downloadFile(context: Context, name: String): Flow<Result<File>> = callbackFlow {
        val file = File(context.filesDir, name)
        if (file.exists()) {
            trySend(Result.success(file))
        } else {
            storage.reference.child("Books/$name")
                .getFile(file)
                .addOnSuccessListener {
                    trySend(Result.success(file))
                }
                .addOnFailureListener {

                    myLog(it.message.toString())
                }
                .addOnProgressListener {
                    val progress = it.bytesTransferred * 100 / it.totalByteCount
                    myLog("progress = $progress")
                }
        }
        awaitClose()
    }

    override fun getAllBooks(): Flow<Result<List<BookData>>> {

        return callbackFlow {

            db.collection("books")
                .get()
                .addOnSuccessListener { querysnabshot ->
                    val list = ArrayList<BookData>()

                    querysnabshot.forEach {
                        val bookData = BookData(
                            title = it.get("title") as String,
                            author = it.get("author") as String,
                            bookUrl = it.get("bookUrl") as String,
                            coverUrl = it.get("coverUrl") as String,
                            genre = it.get("genre") as String,
                            reference = it.get("reference") as String,
                            description = it.get("description") as String,
                            rate = it.get("rate") as String,
                        )
                        list.add(bookData)
//                        if (localStorage?.getFirstApp()!!) {
                            dao.insertBook(bookData.toEntity())
//                        }
                    }
//                    localStorage?.saveFirstApp(false)
                    trySend(Result.success(list))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }
    }

    override fun getBooksByCategory(categoryName: String): Flow<Result<List<BookData>>> =
        callbackFlow {
            db.collection("books")
                .whereEqualTo("genre", categoryName).get()
                .addOnSuccessListener { querysnabshot ->
                    val list = ArrayList<BookData>()

                    querysnabshot.forEach {
                        val bookData = BookData(
                            it.get("author") as String,
                            it.get("bookUrl") as String,
                            it.get("coverUrl") as String,
                            it.get("genre") as String,
                            it.get("reference") as String,
                            it.get("title") as String,
                            it.get("description") as String,
                            it.get("rate") as String
                        )
                        list.add(bookData)
                    }
                    trySend(Result.success(list))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }

    override fun getAllCategorys(): Flow<Result<List<String>>> {
        return callbackFlow {
            db.collection("category")
                .get()
                .addOnSuccessListener { querysnabshot ->
                    val list = ArrayList<String>()

                    querysnabshot.forEach {
                        list.add(it.get("name") as String)
                    }
                    trySend(Result.success(list))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }
    }

    override fun getAllBooksByRate(): Flow<Result<List<BookData>>> {
        return callbackFlow {
            db.collection("books")
                .get()
                .addOnSuccessListener { querysnabshot ->
                    val list = ArrayList<BookData>()

                    querysnabshot.forEach {
                        val bookData = BookData(
                            it.get("author") as String,
                            it.get("bookUrl") as String,
                            it.get("coverUrl") as String,
                            it.get("genre") as String,
                            it.get("reference") as String,
                            it.get("title") as String,
                            it.get("description") as String,
                            it.get("rate") as String
                        )
                        val rate = (it.get("rate") as String).toInt()
                        if (rate >= 4)
                            list.add(bookData)
                    }
                    trySend(Result.success(list))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }
    }

    override fun getSaveBooks(): Flow<Result<List<BookData>>> {
        return callbackFlow {
            db.collection("books")
                .get()
                .addOnSuccessListener { querysnabshot ->
                    val list = ArrayList<BookData>()

                    querysnabshot.forEach {
                        val bookData = BookData(
                            it.get("author") as String,
                            it.get("bookUrl") as String,
                            it.get("coverUrl") as String,
                            it.get("genre") as String,
                            it.get("reference") as String,
                            it.get("title") as String,
                            it.get("description") as String,
                            it.get("rate") as String
                        )
                        val file = File(App.context.filesDir, bookData.reference)
                        if (file.exists()) {
                            list.add(bookData)
                        }
                    }
                    trySend(Result.success(list))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }
    }

    override fun getBooks(): LiveData<List<BookData>> {
        return dao.getBooks()
    }

    override fun getBooksSave(): LiveData<List<BookData>> {
        return dao.getBooksSave()
    }

    override fun getBooksList(): List<BookData> {
        return dao.getBooksList()
    }


    override fun getAllBooksByHomeQuery(query: String): List<BookData> {
        return dao.getAllBooksByHomeQuery(query)
    }

    override fun getAllBooksByExporeCategory(query: String): List<BookData> {
        return dao.getAllBooksByExploreCategory(query)
    }

    override fun updateBook(book: BookData) {
        myLog("UPDATE2")
        dao.updateBook(book.toEntity())
    }

}