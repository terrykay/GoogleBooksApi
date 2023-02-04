package uk.meadowsoftware.googlebooksapi

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import uk.meadowsoftware.googlebooksapi.repository.BookDatabase
import uk.meadowsoftware.googlebooksapi.repository.GoogleBooksRepository
import uk.meadowsoftware.googlebooksapi.repository.GoogleBooksRetrofitService

class BookApplication : Application() {
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            BookDatabase::class.java, "book-database"
        ).build()
    }

    val appModule = module {
        single<GoogleBooksRetrofitService> { GoogleBooksRetrofitService.getInstance() }
        single<RoomDatabase> {
            Room.databaseBuilder(
                applicationContext,
                BookDatabase::class.java, "book-database"
            ).build()
        }
        single<GoogleBooksRepository> { GoogleBooksRepository(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BookApplication)
            modules(appModule)
        }
    }
}