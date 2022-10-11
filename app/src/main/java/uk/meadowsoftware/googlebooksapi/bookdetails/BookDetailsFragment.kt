package uk.meadowsoftware.googlebooksapi.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uk.meadowsoftware.googlebooksapi.constants.HARDBACK
import uk.meadowsoftware.googlebooksapi.constants.SOFTBACK
import uk.meadowsoftware.googlebooksapi.databinding.FragmentBookDetailsBinding
import uk.meadowsoftware.googlebooksapi.databinding.FragmentFirstBinding
import uk.meadowsoftware.googlebooksapi.repository.localmodel.BookModel

class BookDetailsFragment : Fragment() {
    private var _binding: FragmentBookDetailsBinding? = null

    private val binding get() = _binding!!

    private var bookModel: BookModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setBook(bookModel: BookModel) {
        binding.titleEditText.setText(bookModel.title)
        binding.authorsEditText.setText(bookModel.authors.joinToString(separator = ", "))
        binding.publisherEditText.setText(bookModel.publisher)
        binding.isbnEditText.setText(bookModel.isbn)
        when (bookModel.binding) {
            HARDBACK -> {
                binding.hardbackRadioButton.isChecked = true
            }
            SOFTBACK -> {
                binding.softbackRadioButton.isChecked = true
            }
        }
    }

    private fun getBook(): BookModel {
        if (bookModel == null)
            bookModel = BookModel()
        bookModel!!.copy(
            title = binding.titleEditText.text.toString(),
            authors = binding.authorsEditText.text.toString().split(", "),
            publisher = binding.publisherEditText.text.toString(),
            isbn = binding.isbnEditText.text.toString(),
            binding = when {
                binding.hardbackRadioButton.isChecked -> HARDBACK
                binding.softbackRadioButton.isChecked -> SOFTBACK
                else -> ""
            }
        ).also {
            return it
        }
    }

    companion object {
        fun newInstance(bookModel: BookModel? = null): BookDetailsFragment {
            val args = Bundle()
            bookModel?.let {
                args.putParcelable(BOOK_PARAM, bookModel)
            }
            val fragment = BookDetailsFragment()
            fragment.arguments = args
            return fragment
        }

        const val BOOK_PARAM = "BookParam"
    }
}