package project.repositories.jdbi.mappers

import kotlinx.datetime.Instant
import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import project.domain.users.Password
import project.domain.users.User
import java.sql.ResultSet
import java.sql.SQLException
import kotlin.jvm.Throws

class UserMapper : ColumnMapper<User> {
    @Throws(SQLException::class)
    override fun map(r: ResultSet?, columnNumber: Int, ctx: StatementContext?): User? {
        return if(r != null) User(
            id = r.getInt("user_id"),
            name = r.getString("username"),
            email = r.getString("email"),
            password = Password(r.getString("password")),
            description = r.getString("description"),
            createdAt = Instant.fromEpochSeconds(r.getLong("created_at"))
        ) else null
    }
}