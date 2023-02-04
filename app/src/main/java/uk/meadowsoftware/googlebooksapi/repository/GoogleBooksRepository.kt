package uk.meadowsoftware.googlebooksapi.repository

import android.util.Log
import com.example.example.GoogleBooksApiResponse
import retrofit2.Response
import uk.meadowsoftware.googlebooksapi.repository.localmodel.Book

class GoogleBooksRepository(private val retrofitService: GoogleBooksRetrofitService) {
    suspend fun getBookByIsbn(isbn: String): List<Book> {
        val got = retrofitService.getBookByQuery("isbn:$isbn")
        return responseToBookList(got)
    }
    suspend fun getBooksByAuthor(author: String): List<Book> {
        val got = retrofitService.getBookByQuery("inauthor:$author", maxResults = 30)
        Log.d("GBR", "$got")
        return responseToBookList(got)
    }
    suspend fun getBooksByTitle(title: String): List<Book> {
        val got = retrofitService.getBookByQuery("intitle:$title")
        return responseToBookList(got)
    }

    private fun responseToBookList(response: Response<GoogleBooksApiResponse>): List<Book> {
        val bookList = mutableListOf<Book>()
        response.body()?.items?.forEach {
            bookList.add(Book(it.volumeInfo!!))
        }
        return bookList
    }

}