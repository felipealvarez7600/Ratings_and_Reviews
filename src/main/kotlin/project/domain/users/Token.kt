package project.domain.users

import kotlinx.datetime.Instant


data class Token(
    val hashToken: TokenValidationInfo,
    val userId: Int,
    val createdAt: Instant,
    val lastUsedAt: Instant
)