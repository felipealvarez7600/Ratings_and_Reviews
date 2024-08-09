package project.repositories.jdbi.mappers

import kotlinx.datetime.Instant
import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import project.domain.users.Token
import project.domain.users.TokenValidationInfo
import java.sql.ResultSet

class TokenMapper : ColumnMapper<Token> {
    override fun map(r: ResultSet?, columnNumber: Int, ctx: StatementContext): Token? {
        return if(r != null) Token(
            userId = r.getInt("user_id"),
            hashToken = TokenValidationInfo(r.getString("token")),
            createdAt = Instant.fromEpochSeconds(r.getLong("created_at")),
            lastUsedAt = Instant.fromEpochSeconds(r.getLong("last_used_at"))
        ) else null
    }
}