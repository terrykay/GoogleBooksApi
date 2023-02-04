package uk.meadowsoftware.googlebooksapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import uk.meadowsoftware.googlebooksapi.bookcardview.BookCardAdapter
import uk.meadowsoftware.googlebooksapi.databinding.FragmentFirstBinding
import uk.meadowsoftware.googlebooksapi.repository.GoogleBooksRepository
import uk.meadowsoftware.googlebooksapi.repository.localmodel.Book

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val r  : GoogleBooksRepository  by inject() //(GoogleBooksRetrofitService.getInstance())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_BookDetailsFragment)
        }

       // val r  = GoogleBooksRepository(GoogleBooksRetrofitService.getInstance())
        lifecycleScope.launch {
            val listByAuthor = r.getBooksByAuthor("Terry+Pratchett")

            binding.recyclerView.run {
                adapter = BookCardAdapter().apply {
                    setBookList(listByAuthor)
                }
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}