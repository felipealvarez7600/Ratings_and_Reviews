package project.domain

data class Episode (
    val id: Int,
    val title: String,
    val description: String,
    val releaseDate: Int,
    val duration: Long,
    val rating: Int
)