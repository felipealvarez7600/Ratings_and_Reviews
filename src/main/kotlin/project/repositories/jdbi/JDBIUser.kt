package project.repositories.jdbi

import kotlinx.datetime.Instant
import org.jdbi.v3.core.Handle
import project.domain.users.Token
import project.domain.users.TokenValidationInfo
import project.domain.users.User
import project.repositories.UserRepository
import org.jdbi.v3.core.kotlin.mapTo

class JDBIUser (private val handle: Handle) : UserRepository {
    override fun storeUser(user: User): Int? {
        return handle.createUpdate(
            """
            INSERT INTO users (username, email, password, description, created_at)
            VALUES (:username, :email, :password, :description, :created_at)
            """
        )
            .bind("username", user.name)
            .bind("email", user.email)
            .bind("password", user.password.hashedPassword)
            .bind("description", user.description)
            .bind("created_at", user.createdAt.epochSeconds)
            .executeAndReturnGeneratedKeys("user_id")
            .mapTo(Int::class.java)
            .one()
    }

    override fun getUserByEmail(email: String): User? {
        return handle.createQuery(
            """
            SELECT user_id, username, email, password, description, created_at
            FROM users
            WHERE email = :email
            """
        )
            .bind("email", email)
            .mapTo<User>()
            .singleOrNull()
    }

    override fun getUserById(id: Int): User? {
        return handle.createQuery(
            """
            SELECT user_id, username, email, password, description, created_at
            FROM users
            WHERE user_id = :user_id
            """
        )
            .bind("user_id", id)
            .mapTo<User>()
            .singleOrNull()
    }

    override fun getUserByToken(token: String): User? {
        return handle.createQuery(
            """
            SELECT u.user_id, u.username, u.email, u.password, u.description, u.created_at
            FROM users u
            JOIN tokens t ON u.user_id = t.user_id
            WHERE t.token = :token
            """
        )
            .bind("token", token)
            .mapTo<User>()
            .singleOrNull()
    }

    override fun getTokenByTokenValidationInfo(tokenValidationInfo: TokenValidationInfo): Token? {
        return handle.createQuery(
            """
            SELECT user_id, token, created_at, last_used_at
            FROM tokens
            WHERE token = :token
            """
        )
            .bind("token", tokenValidationInfo.validationInfo)
            .mapTo<Token>()
            .singleOrNull()
    }

    override fun updateUser(user: User): Int {
        return handle.createUpdate(
            """
            UPDATE users
            SET username = :username, email = :email, password = :password, description = :description
            WHERE user_id = :user_id
            """
        )
            .bind("username", user.name)
            .bind("email", user.email)
            .bind("password", user.password.hashedPassword)
            .bind("description", user.description)
            .bind("user_id", user.id)
            .execute()
    }

    override fun deleteUser(id: Int): Int {
        return handle.createUpdate(
            """
            DELETE FROM users
            WHERE user_id = :user_id
            """
        )
            .bind("user_id", id)
            .execute()
    }

    override fun createToken(token: Token): Int {
        return handle.createUpdate(
            """
            INSERT INTO tokens (user_id, token, created_at, last_used_at)
            VALUES (:user_id, :token, :created_at, :last_used_at)
            """
        )
            .bind("user_id", token.userId)
            .bind("token", token.hashToken)
            .bind("created_at", token.createdAt.epochSeconds)
            .bind("last_used_at", token.lastUsedAt.epochSeconds)
            .execute()
    }

    override fun updateTokenLastUsed(token: Token, now: Instant): Int {
        return handle.createUpdate(
            """
            UPDATE tokens
            SET last_used_at = :last_used_at
            WHERE token = :token
            """
        )
            .bind("last_used_at", now.epochSeconds)
            .bind("token", token.hashToken)
            .execute()
    }

    override fun removeTokenByValidationInfo(tokenValidationInfo: TokenValidationInfo): Int {
        return handle.createUpdate(
            """
            DELETE FROM tokens
            WHERE token = :token
            """
        )
            .bind("token", tokenValidationInfo.validationInfo)
            .execute()
    }
}