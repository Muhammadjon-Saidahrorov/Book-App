package uz.gita.bookappmn.presentation.ui.book

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappmn.data.sourse.LocalStorage
import uz.gita.bookappmn.R
import uz.gita.bookappmn.databinding.ScreenBookBinding
import uz.gita.bookappmn.domain.repository.AppRepository
import uz.gita.bookappmn.domain.repository.impl.AppRepositoryImpl
import uz.gita.bookappmn.presentation.viewmodel.BookViewModel
import uz.gita.bookappmn.presentation.viewmodel.ExploreViewModel
import uz.gita.bookappmn.presentation.viewmodel.impl.BookViewModelImpl
import uz.gita.bookappmn.presentation.viewmodel.impl.ExploreViewModelImpl
import uz.gita.bookappmn.utils.checkPermissions
import uz.gita.bookappmn.utils.showToast
import java.io.File

@AndroidEntryPoint
class BookScreen : Fragment(R.layout.screen_book) {
    private val binding by viewBinding(ScreenBookBinding::bind)
    private val args by navArgs<BookScreenArgs>()
    private val viewModel: BookViewModel by viewModels<BookViewModelImpl>()
    private val localStorage = LocalStorage.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookData = args.bookData

        binding.bookTitle.text = bookData?.title
        binding.bookAuthor.text = bookData?.author
        binding.bookDescription.text = bookData?.description

        Glide
            .with(requireContext())
            .load(bookData?.coverUrl)
            .centerCrop()
            .into(binding.imgBook)

        val file = File(requireContext().filesDir, bookData!!.reference)
        if (file.exists()) {
            binding.download.setImageResource(R.drawable.check)
            binding.openPdf.visibility = View.VISIBLE
        } else {
            binding.download.setImageResource(R.drawable.down)
            binding.openPdf.visibility = View.GONE

            binding.download.setOnClickListener {

                val a = CircularProgressDrawable(requireActivity())
                a.strokeWidth = 5f
                a.centerRadius = 30f
                a.start()

                binding.download.setImageDrawable(a)

//                checkPermissions(arrayListOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    requireContext().showToast("Waiting...")

                    viewModel.download(requireContext(), bookData)
//                }
            }


        }

        binding.imgBook.setOnClickListener {
            if (file.exists()) {
                localStorage?.saveFirstLogic(false)
                localStorage?.saveLogicRead(true)
                findNavController().navigate(
                    BookScreenDirections.actionBookScreenToPdfActivity(
                        bookData
                    )
                )
            } else {
                requireContext().showToast("Download book!")
            }
        }

        binding.openPdf.setOnClickListener {
            if (file.exists()) {
                localStorage?.saveFirstLogic(false)
                localStorage?.saveLogicRead(true)
                findNavController().navigate(
                    BookScreenDirections.actionBookScreenToPdfActivity(
                        bookData
                    )
                )
            } else {
                requireContext().showToast("Download book!")
            }
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.check.observe(viewLifecycleOwner, checkObserver)
        viewModel.errorData.observe(viewLifecycleOwner, errorObserver)
        viewModel.downloadToast.observe(viewLifecycleOwner, downloadToastObserver)
    }

    private val checkObserver = Observer<Boolean> {
        if (it) {
            binding.download.setImageResource(R.drawable.check)
            binding.openPdf.visibility = View.VISIBLE
        }
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
    }

    private val downloadToastObserver = Observer<String> {
        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
    }


}
