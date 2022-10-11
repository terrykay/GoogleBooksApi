package uk.meadowsoftware.googlebooksapi.repository

class GoogleBooksRepository(private val retrofitService: GoogleBooksRetrofitService) {
    suspend fun getBookByIsbn(isbn: String) = retrofitService.getBookByQuery("isbn:$isbn")
    suspend fun getBooksByAuthor(author: String) = retrofitService.getBookByQuery("inauthor:$author")
    suspend fun getBooksByTitle(title: String) = retrofitService.getBookByQuery("intitle:$title")
}