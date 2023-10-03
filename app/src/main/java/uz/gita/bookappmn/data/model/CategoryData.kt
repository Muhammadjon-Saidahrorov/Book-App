package uz.gita.bookappmn.data.model

class CategoryData(
    val id: String = "",
    val name: String,
    val listInner: List<BookData>
)