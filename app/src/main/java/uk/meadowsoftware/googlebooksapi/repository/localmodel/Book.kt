package uk.meadowsoftware.googlebooksapi.repository.localmodel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.example.VolumeInfo
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Book(
    @PrimaryKey val uid: Int,
    val title: String,
    val publisher: String,
    val publishedDate: String,
    val binding: String,
    @TypeConverters(AuthorListConverter::class)
    val authors: List<String>,
    val thumbnailUrl: String?,
    val isbn: String?,
    val owned: Boolean = false
) : Parcelable {
    constructor(apiModel: VolumeInfo)
            :
            this(
                0,
                apiModel.title.orEmpty(),
                apiModel.publisher.orEmpty(),
                apiModel.publishedDate.orEmpty(),
                "",
                apiModel.authors,
                apiModel.imageLinks?.thumbnail,
                apiModel.industryIdentifiers.joinToString(separator = ", ")
            )
    constructor(): this (0,"", "", "", "", emptyList(), null, null, false)
}