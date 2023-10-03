package uz.gita.bookappmn.presentation.ui.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappmn.R
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.data.model.TextData
import uz.gita.bookappmn.databinding.ScreenExploreBinding
import uz.gita.bookappmn.presentation.adapter.SaveBookAdapter
import uz.gita.bookappmn.presentation.adapter.TextAdapter
import uz.gita.bookappmn.presentation.viewmodel.ExploreViewModel
import uz.gita.bookappmn.presentation.viewmodel.impl.ExploreViewModelImpl
import uz.gita.bookappmn.utils.myLog

@AndroidEntryPoint
class ExploreScreen : Fragment(R.layout.screen_explore) {
    private val binding by viewBinding(ScreenExploreBinding::bind)
    private val viewModel: ExploreViewModel by viewModels<ExploreViewModelImpl>()
    private val adapter = SaveBookAdapter()
    private val adapterText = TextAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = adapter
        binding.listText.adapter = adapterText
        binding.list.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadBooks.observe(viewLifecycleOwner, loadBooks)
        viewModel.loadCategory.observe(viewLifecycleOwner, loadCategory)
        viewModel.searchedWordsLiveData.observe(viewLifecycleOwner, loadSearchBooks)
    }


    private val loadBooks = Observer<List<BookData>> { dataList ->
        adapter.submitList(dataList)

        adapter.setClickListener {
            findNavController().navigate(ExploreScreenDirections.actionExploreScreenToBookScreen(it))
        }
    }

    private val loadCategory = Observer<List<TextData>> { dataList ->
        adapterText.submitList(dataList)

        adapterText.setClickListener {
            viewModel.searchingNote(it.name)
        }
    }

    private val loadSearchBooks = Observer<List<BookData>> { dataList ->
        adapter.submitList(dataList)
    }
}