package uz.gita.bookappmn.data.sourse.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.bookappmn.data.model.BookData

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val title: String = "",
    val author: String = "",
    val bookUrl: String = "",
    val coverUrl: String = "",
    val genre: String = "",
    val reference: String = "",
    val description: String = "",
    val rate: String = "",
    val save: String = "0"
) {
    fun toData() = BookData(
        title = title,
        author = author,
        bookUrl = bookUrl,
        coverUrl = coverUrl,
        genre = genre,
        reference = reference,
        description = description,
        rate = rate,
        save = save
    )
}
