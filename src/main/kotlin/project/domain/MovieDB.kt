package project.domain

import java.time.Instant

data class MovieDB(
    val id: Int,
    val title: String,
    val description: String,
    val releaseDate: Instant,
    val duration: Long,
    val rating: Int,
)

