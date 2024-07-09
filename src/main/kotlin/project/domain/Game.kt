package project.domain

import java.time.Instant

data class Game (
    val id: Int,
    val title: String,
    val description: String,
    val releaseDate: Instant,
    val rating: Int
)