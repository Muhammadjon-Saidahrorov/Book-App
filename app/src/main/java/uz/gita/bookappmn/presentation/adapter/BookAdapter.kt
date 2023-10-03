package uz.gita.bookappmn.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.bookappmn.app.App
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.databinding.ItemBookBinding


class BookAdapter : ListAdapter<BookData, BookAdapter.EventHolder>(EventDiffUtil) {

    private var clickListener: ((BookData) -> Unit)? = null

    object EventDiffUtil : DiffUtil.ItemCallback<BookData>() {
        override fun areItemsTheSame(oldItem: BookData, newItem: BookData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BookData, newItem: BookData): Boolean {
            return oldItem.title == newItem.title || oldItem.reference == newItem.reference
        }

    }

    inner class EventHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                clickListener?.invoke(getItem(adapterPosition))
            }

        }

        fun bind() {
            getItem(adapterPosition).apply {

                Glide
                    .with(App.context)
                    .load(this.coverUrl)
                    .centerCrop()
                    .into(binding.imgBook)

                binding.txtBookName.text = this.title
                binding.txtBookAuther.text = this.author
//                binding.starText.text = this.rate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder = EventHolder(
        ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind()
    }

    fun setClickListener(block: (BookData) -> Unit) {
        clickListener = block
    }

}