package uk.meadowsoftware.googlebooksapi.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uk.meadowsoftware.googlebooksapi.repository.localmodel.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    @Query("SELECT * FROM book WHERE uid IN (:bookIds)")
    fun loadAllByIds(bookIds: IntArray): List<Book>

    @Query("SELECT * FROM book WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): Book

    @Insert
    fun insertAll(vararg books: Book)

    @Delete
    fun delete(book: Book)

}