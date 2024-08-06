package project.domain.users

data class AuthenticatedUser(
    val user: User,
    val token: Token
)
