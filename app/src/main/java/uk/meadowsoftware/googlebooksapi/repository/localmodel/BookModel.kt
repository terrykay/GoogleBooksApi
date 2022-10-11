package uk.meadowsoftware.googlebooksapi.repository.localmodel

import android.os.Parcelable
import com.example.example.VolumeInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookModel(
    val title: String,
    val publisher: String,
    val publishedDate: String,
    val binding: String,
    val authors: List<String>,
    val thumbnailUrl: String?,
    val isbn: String?,
) : Parcelable {
    constructor(apiModel: VolumeInfo)
            :
            this(
                apiModel.title.orEmpty(),
                apiModel.publisher.orEmpty(),
                apiModel.publishedDate.orEmpty(),
                "",
                apiModel.authors,
                apiModel.imageLinks?.thumbnail,
                apiModel.industryIdentifiers.joinToString(separator = ", ")
            )
    constructor(): this ("", "", "", "", emptyList(), null, null)
}