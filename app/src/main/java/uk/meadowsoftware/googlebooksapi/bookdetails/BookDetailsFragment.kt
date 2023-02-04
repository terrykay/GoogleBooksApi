package uk.meadowsoftware.googlebooksapi.bookdetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.launch
import uk.meadowsoftware.googlebooksapi.constants.HARDBACK
import uk.meadowsoftware.googlebooksapi.constants.SOFTBACK
import uk.meadowsoftware.googlebooksapi.databinding.FragmentBookDetailsBinding
import uk.meadowsoftware.googlebooksapi.repository.GoogleBooksRepository
import uk.meadowsoftware.googlebooksapi.repository.GoogleBooksRetrofitService
import uk.meadowsoftware.googlebooksapi.repository.localmodel.Book

class BookDetailsFragment : Fragment() {
    private var _binding: FragmentBookDetailsBinding? = null

    private val binding get() = _binding!!

    private fun searchIsbnUseCase(isbn: String) {

    }

    private var bookModel: Book? = null

    // Temporary
    val r  = GoogleBooksRepository(GoogleBooksRetrofitService.getInstance())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)

        binding.bookDetailsActionButton.setOnClickListener {
            scanBarcode()
        }
        binding.buttonSearch.setOnClickListener {
            Toast.makeText(requireContext(), "Searcging", Toast.LENGTH_LONG).show()
            // Todo - Viewmodel
            lifecycleScope.launch {
                val got = r.getBookByIsbn(binding.isbnEditText.text.toString())

                binding.titleEditText.setText(got.first().title)
                binding.publisherEditText.setText(got.first().publisher)
                Log.d("MainActivity", "resp - ${got}")
            }
        }
        return binding.root
    }

    fun setBook(bookModel: Book) {
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

    fun getBook(): Book {
        if (bookModel == null)
            bookModel = Book()
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

    private fun scanBarcode() {
        IntentIntegrator.forSupportFragment(this).apply {
            setBeepEnabled(false)
            setCameraId(0)
            setPrompt("SCAN")
            setBarcodeImageEnabled(false)
            initiateScan()
        }
    }

    // Deal with Barcode scanner results
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) { // else cancelled
                binding.isbnEditText.setText(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    companion object {
        fun newInstance(bookModel: Book? = null): BookDetailsFragment {
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