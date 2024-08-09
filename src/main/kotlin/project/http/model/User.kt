package project.http.model

/**
 * Data class "UserCreateInputModel" represents the information sent to the database about the User to create one.
 * @property username represents the name of it.
 * @property password represents the password chosen by the user.
 */
data class UserCreateInputModel(val username: String, val email: String, val password: String)

/**
 * Data class "UserCreateTokenInputModel" represents the information send to the database about the user to create a token there.
 * @property username represents the name of the user.
 * @property password represents the password of the user.
 */
data class UserCreateTokenInputModel(val email:String, val password: String)

/**
 * Data class "UserHomeOutputModel" represents the information sent by the database about the user.
 * @property id represents the id of the user.
 * @property username represents the name of the user.
 * @param logout represent the path for the logout uri.
 * @param play represent the path for the play uri.
 * @param joinGame represent the path for the joinGame uri.
 */
data class UserHomeOutputModel(
    val id: Int,
    val username: String,
    val logout: Link,
)

/**
 * Data class "UserTokenCreateOutputModel" represents the information gave by the database when creating a new token for a user.
 * @property token represents the token created.
 */
data class UserTokenCreateOutputModel(val token: String, val userHome: Link)

/**
 * Data class "UserCreateOutputModel" represent the CreateUser information when created.
 * @property id represent the id of the user.
 * @property login represent the link for the login.
 */
data class UserCreateOutputModel(val id: Int, val login: Link)

/**
 * Data class "LogoutOutputModel" represent the logout information when it is finished the logout.
 * @property success represent if it was success of the logout or not.
 * @property home represent the link of the home page.
 */
data class LogoutOutputModel(val success: Boolean, val home: Link)