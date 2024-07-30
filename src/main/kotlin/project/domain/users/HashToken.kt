package project.domain.users

import java.time.Instant

data class HashToken(
    val hashToken: String,
    val userId: Int,
    val lastUsedAt: Instant
)