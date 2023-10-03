package uz.gita.bookappmn.presentation.ui.save

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappmn.R
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.data.sourse.LocalStorage
import uz.gita.bookappmn.databinding.ScreenSaveBinding
import uz.gita.bookappmn.presentation.adapter.SaveBookAdapter
import uz.gita.bookappmn.presentation.viewmodel.SaveViewModel
import uz.gita.bookappmn.presentation.viewmodel.impl.SaveViewModelImpl

@AndroidEntryPoint
class SaveScreen : Fragment(R.layout.screen_save) {
    private val binding by viewBinding(ScreenSaveBinding::bind)
    private val viewModel: SaveViewModel by viewModels<SaveViewModelImpl>()
    private val adapter = SaveBookAdapter()
    private val localStorage = LocalStorage.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadBooks.observe(viewLifecycleOwner, loadBooks)

        if (localStorage?.getFirstLogic() == true) {
            binding.liner.visibility = View.GONE
            binding.emptyBook.visibility = View.VISIBLE
        } else {
            binding.liner.visibility = View.VISIBLE
            binding.emptyBook.visibility = View.GONE

            binding.bookName.text = localStorage?.getBookName()
            binding.bookAuthor.text = localStorage?.getBookAuthor()

            Glide
                .with(requireContext())
                .load(localStorage?.getBookImg())
                .centerCrop()
                .into(binding.bookImg)
        }

        binding.liner.setOnClickListener {
            localStorage?.saveLogicRead(false)
            findNavController().navigate(R.id.action_saveScreen_to_pdfActivity2)
        }
    }

    private val loadBooks = Observer<List<BookData>> { bookData ->
        adapter.submitList(bookData)
        if (bookData.isNotEmpty()){
            binding.emptyDownload.visibility = View.GONE
        }

        adapter.setClickListener {
            findNavController().navigate(SaveScreenDirections.actionSaveScreenToBookScreen(it))
        }

    }

}