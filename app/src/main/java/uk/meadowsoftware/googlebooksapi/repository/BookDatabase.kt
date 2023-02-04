package uk.meadowsoftware.googlebooksapi.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.meadowsoftware.googlebooksapi.repository.localmodel.AuthorListConverter
import uk.meadowsoftware.googlebooksapi.repository.localmodel.Book

@Database(entities = [Book::class], version = 1)
@TypeConverters(AuthorListConverter::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}