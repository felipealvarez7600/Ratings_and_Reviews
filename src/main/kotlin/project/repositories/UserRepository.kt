package project.repositories

import kotlinx.datetime.Instant
import project.domain.users.Token
import project.domain.users.TokenValidationInfo
import project.domain.users.User

interface UserRepository {
    fun storeUser(user: User): Int?
    fun getUserByEmail(email: String): User?
    fun getUserById(id: Int): User?
    fun getUserByToken(token: String): User?
    fun updateUser(user: User): Int
    fun deleteUser(id: Int): Int
    fun createToken(token: Token) : Int
    fun updateTokenLastUsed(token: Token, now: Instant) : Int
    fun removeTokenByValidationInfo(tokenValidationInfo: TokenValidationInfo): Int
}