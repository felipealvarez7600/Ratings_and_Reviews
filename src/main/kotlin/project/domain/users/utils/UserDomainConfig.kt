package project.domain.users.utils

import kotlin.time.Duration

/**
 * Data class "UsersDomainConfig" representing the Configuration of the user by token.
 * @property tokenSizeInBytes represents the size of the token.
 * @property tokenTtl represents the time of life for that token.
 * @property tokenRollingTtl represents the time that passed the token.
 * @property maxTokensPerUser represents the number maximum of tokens the user can have.
 */
data class UsersDomainConfig(
    val tokenSizeInBytes: Int,
    val tokenTtl: Duration,
    val tokenRollingTtl: Duration,
    val maxTokensPerUser: Int
) {
    /**
     * Initial check of the information if it was initialized with the information correctly.
     */
    init {
        require(tokenSizeInBytes > 0)
        require(tokenTtl.isPositive())
        require(tokenRollingTtl.isPositive())
        require(maxTokensPerUser > 0)
    }
}