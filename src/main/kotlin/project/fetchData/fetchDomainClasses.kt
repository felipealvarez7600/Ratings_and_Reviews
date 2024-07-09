package project.fetchData

import kotlinx.datetime.Instant

data class MovieFetch(
    val title: String,
    val description: String,
    val releaseDate: String,
    val duration: Long,
    val onlineRating: Int
)