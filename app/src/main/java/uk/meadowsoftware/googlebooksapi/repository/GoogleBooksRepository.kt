package uk.meadowsoftware.googlebooksapi.repository

class GoogleBooksRepository(private val retrofitService: GoogleBooksRetrofitService) {
    suspend fun getBookByIsbn(isbn: String) = retrofitService.getBookByIsbn("isbn:$isbn")
}