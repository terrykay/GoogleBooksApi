package uk.meadowsoftware.googlebooksapi.repository

import android.graphics.Movie
import com.example.example.GoogleBooksApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksRetrofitService {
    @GET("books/v1/volumes")
    suspend fun getBookByQuery(@Query("q", encoded = true)queryString: String,
                               @Query("maxResults")maxResults: Int = 10,
                               @Query("startIndex")startIndex: Int = 0
    ) : Response<GoogleBooksApiResponse>

    companion object {
        var retrofitService: GoogleBooksRetrofitService? = null
        fun getInstance() : GoogleBooksRetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(GoogleBooksRetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}