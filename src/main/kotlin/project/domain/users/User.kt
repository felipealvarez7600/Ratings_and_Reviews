package project.domain.users

import kotlinx.datetime.Instant


data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: Password,
    val description: String? = null,
    val createdAt: Instant
)