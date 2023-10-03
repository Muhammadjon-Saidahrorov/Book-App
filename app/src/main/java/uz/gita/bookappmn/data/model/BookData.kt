package uz.gita.bookappmn.data.model

import android.os.Parcel
import android.os.Parcelable
import uz.gita.bookappmn.data.sourse.local.entity.BookEntity

data class BookData(
    val title: String = "",
    val author: String = "",
    val bookUrl: String = "",
    val coverUrl: String = "",
    val genre: String = "",
    val reference: String = "",
    val description: String = "",
    val rate: String = "",
    var save: String = "0"
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(bookUrl)
        parcel.writeString(coverUrl)
        parcel.writeString(genre)
        parcel.writeString(reference)
        parcel.writeString(description)
        parcel.writeString(rate)
        parcel.writeString(save)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookData> {
        override fun createFromParcel(parcel: Parcel): BookData {
            return BookData(parcel)
        }

        override fun newArray(size: Int): Array<BookData?> {
            return arrayOfNulls(size)
        }
    }

    fun toEntity(): BookEntity = BookEntity(
        title = title,
        author = author,
        bookUrl = bookUrl,
        coverUrl = coverUrl,
        genre = genre,
        description = description,
        reference = reference,
        rate = rate,
        save = save
    )
}