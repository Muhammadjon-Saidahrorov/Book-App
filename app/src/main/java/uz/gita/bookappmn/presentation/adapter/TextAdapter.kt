package uz.gita.bookappmn.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.bookappmn.R
import uz.gita.bookappmn.data.model.TextData
import uz.gita.bookappmn.databinding.ItemCategoryBinding

class TextAdapter : ListAdapter<TextData, TextAdapter.EventHolder>(EventDiffUtil) {

    private var clickListener: ((TextData) -> Unit)? = null

    object EventDiffUtil : DiffUtil.ItemCallback<TextData>() {
        override fun areItemsTheSame(oldItem: TextData, newItem: TextData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TextData, newItem: TextData): Boolean {
            return oldItem.name == newItem.name
        }

    }

    inner class EventHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            getItem(adapterPosition).apply {
                val data = getItem(adapterPosition)

                if (data.checked) {
                    binding.root.background =
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_category_checked)
                    binding.txtCategory.setTextColor(Color.WHITE)
                } else {
                    binding.root.background =
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_category_unchecked)
                    binding.txtCategory.setTextColor(Color.BLACK)
                }
                binding.txtCategory.text = this.name
            }
        }

        init {
            binding.root.setOnClickListener {

                currentList.forEach {
                    it.checked = false
                }
                getItem(adapterPosition).checked = true
                notifyItemRangeChanged(0,currentList.size)
                clickListener?.invoke(getItem(adapterPosition))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder = EventHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind()
    }

    fun setClickListener(block: (TextData) -> Unit) {
        clickListener = block
    }

}