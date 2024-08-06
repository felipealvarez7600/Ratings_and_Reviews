package project.domain.users

import kotlinx.datetime.Instant

data class TokenExternalInfo(
    val tokenValue: String,
    val tokenExpiration: Instant
)
