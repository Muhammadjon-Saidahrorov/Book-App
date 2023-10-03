package uz.gita.bookappmn.presentation.ui.home

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappmn.data.sourse.LocalStorage
import uz.gita.bookappmn.R
import uz.gita.bookappmn.data.model.BookData
import uz.gita.bookappmn.databinding.ScreenHomeBinding
import uz.gita.bookappmn.presentation.adapter.BookAdapter
import uz.gita.bookappmn.presentation.adapter.SaveBookAdapter
import uz.gita.bookappmn.presentation.viewmodel.HomeViewModel
import uz.gita.bookappmn.presentation.viewmodel.impl.HomeViewModelImpl
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter = BookAdapter()
    private val adapterSave = SaveBookAdapter()
    private var logic = true


    private val listImage = arrayListOf(
        SlideModel(R.drawable.img_1, ScaleTypes.FIT),
        SlideModel(R.drawable.img_2, ScaleTypes.FIT),
        SlideModel(R.drawable.img_3, ScaleTypes.FIT),
        SlideModel(R.drawable.img_4, ScaleTypes.FIT),
        SlideModel(R.drawable.img_5, ScaleTypes.FIT)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (logic) {
            binding.list.adapter = adapter
        } else {
            binding.list.adapter = adapterSave
        }

        binding.imageSlider.setImageList(listImage)


        viewModel.loadBooks()

        viewModel.searchedWordsLiveData.observe(viewLifecycleOwner, searchingNotesObserver)
        viewModel.loadBooks.observe(viewLifecycleOwner, loadBooksObserve)

        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (query.isNotEmpty()) {
                            viewModel.searchingNote("%${it.trim()}%")
                            binding.emptyDownload.visibility = View.VISIBLE
                        } else {
                            binding.emptyDownload.visibility = View.GONE
                            if (logic) {
                                adapter.submitList(viewModel.loadBooks.value)
                            } else {
                                adapterSave.submitList(viewModel.loadBooks.value)
                            }
                        }
                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        if (newText.isNotEmpty()) {
                            binding.emptyDownload.visibility = View.VISIBLE
                            viewModel.searchingNote("%${it.trim()}%")
                        } else {
                            binding.emptyDownload.visibility = View.GONE
                            if (logic) {
                                adapter.submitList(viewModel.loadBooks.value)
                            } else {
                                adapterSave.submitList(viewModel.loadBooks.value)
                            }
                        }
                    }

                    return true
                }

            })
        }

        binding.right.setOnClickListener {
            logic = !logic
            binding.list.layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT

            if (logic) {
                binding.list.adapter = adapter
                binding.list.layoutManager = LinearLayoutManager(
                    requireContext(),
                    HORIZONTAL,
                    false
                )
                adapterSave.submitList(viewModel.loadBooks.value)
                adapter.setClickListener {
                    findNavController().navigate(HomeScreenDirections.actionHomeScreenToBookScreen(it))
                }
                it.rotation = 0f
            } else {
                it.rotation = 90f
                binding.list.adapter = adapterSave
                binding.list.layoutParams.height = 0
                binding.list.layoutManager = LinearLayoutManager(requireContext())
                adapterSave.submitList(viewModel.loadBooks.value)
                adapterSave.setClickListener {
                    findNavController().navigate(HomeScreenDirections.actionHomeScreenToBookScreen(it))
                }
            }
        }
    }

    private val loadBooksObserve = Observer<List<BookData>> { bookData ->

        if (bookData.isNotEmpty()) {
            binding.emptyDownload.visibility = View.GONE
        } else {
            binding.emptyDownload.visibility = View.VISIBLE
        }

        if (logic) {
            binding.list.adapter = adapter
            binding.list.layoutManager = LinearLayoutManager(
                requireContext(),
                HORIZONTAL,
                false
            )
            adapter.submitList(bookData)
            adapter.setClickListener {
                findNavController().navigate(HomeScreenDirections.actionHomeScreenToBookScreen(it))
            }
            binding.right.rotation = 0f
        } else {
            binding.right.rotation = 90f
            binding.list.adapter = adapterSave
            binding.list.layoutParams.height = 0
            binding.list.layoutManager = LinearLayoutManager(requireContext())
            adapterSave.submitList(bookData)
            adapterSave.setClickListener {
                findNavController().navigate(HomeScreenDirections.actionHomeScreenToBookScreen(it))
            }
        }
    }

    private val searchingNotesObserver = Observer<List<BookData>> {bookData->
        if (bookData.isNotEmpty()) {
            binding.emptyDownload.visibility = View.GONE
        } else {
            binding.emptyDownload.visibility = View.VISIBLE
        }

        if (logic) {
            binding.list.adapter = adapter
            binding.list.layoutManager = LinearLayoutManager(
                requireContext(),
                HORIZONTAL,
                false
            )
            adapter.submitList(bookData)
            binding.right.rotation = 0f
        } else {
            binding.right.rotation = 90f
            binding.list.adapter = adapterSave
            binding.list.layoutParams.height = 0
            binding.list.layoutManager = LinearLayoutManager(requireContext())

            adapterSave.submitList(bookData)
        }
    }



}

