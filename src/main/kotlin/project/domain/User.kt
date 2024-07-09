package project.domain

import java.time.Instant

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: HashPassword,
    val description: String? = null,
    val createdAt: Instant
)