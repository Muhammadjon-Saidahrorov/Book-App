package uz.gita.bookappmn.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.bookappmn.app.App
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.data.model.CategoryData
import uz.gita.bookappmn.databinding.ItemOuterBinding

class CategoryAdapter : ListAdapter<CategoryData, CategoryAdapter.EventHolder>(EventDiffUtil) {

    private var clickListener: ((BookData) -> Unit)? = null

    object EventDiffUtil : DiffUtil.ItemCallback<CategoryData>() {

        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.name == newItem.name
        }

    }

    inner class EventHolder(private val binding: ItemOuterBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind() {
            getItem(adapterPosition).apply {
                binding.textCategory.text = this.name

                val adapter = BookAdapter()
                binding.list.adapter = adapter
                binding.list.layoutManager = LinearLayoutManager(App.context, LinearLayoutManager.HORIZONTAL, false)

                adapter.submitList(this.listInner)
                adapter.setClickListener{
                    clickListener?.invoke(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder = EventHolder(
        ItemOuterBinding.inflate(
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