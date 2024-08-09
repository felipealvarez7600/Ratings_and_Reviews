package project.http

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.domain.users.AuthenticatedUser
import project.http.model.*
import project.services.TokenError
import project.services.UserError
import project.services.UserService
import project.utils.Failure
import project.utils.Success


@RestController
class UserController (private val userService: UserService) {

    @PostMapping(PathTemplate.Users.CREATE)
    fun create(@RequestBody input: UserCreateInputModel): ResponseEntity<*> =
        when(val res = userService.createUser(input.username, input.email, input.password)){
            is Success -> ResponseEntity.status(201)
                .body(UserCreateOutputModel(res.value, Link("login", PathTemplate.Users.LOGIN)))
            is Failure -> handleUserFailureResponse(res.value)
        }

    @PostMapping(PathTemplate.Users.LOGIN)
    fun login(
        @RequestBody input: UserCreateTokenInputModel,
        response: HttpServletResponse
    ): ResponseEntity<*> {
        return when (val res = userService.createToken(input.email, input.password)) {
            //Response positive from the server if everything worked well about the token creation.
            is Success -> {
                response.addCookie(
                    Cookie("authToken", res.value.tokenValue).apply {
                        isHttpOnly = true
                        secure = true
                        maxAge = 3600
                    }
                )
                ResponseEntity.status(201)
                    .body(UserTokenCreateOutputModel(res.value.tokenValue, Link("userHome", PathTemplate.Users.HOME)))
            }
            //Response negative from the server if something went wrong about the token creation.
            is Failure -> handleTokenFailureResponse(res.value)
        }
    }

    @DeleteMapping(PathTemplate.Users.LOGOUT)
    fun logout(
        user: AuthenticatedUser,
        response: HttpServletResponse
    ): ResponseEntity<*> {
        return when (val res = userService.removeToken(user.token.hashToken.validationInfo)) {
            //Response positive from the server if everything worked well about the token removal.
            is Success -> {
                response.addCookie(
                    Cookie("authToken", "").apply {
                        isHttpOnly = true
                        secure = true
                        maxAge = 0
                    }
                )
                ResponseEntity.ok(LogoutOutputModel(res.value, Link("home", PathTemplate.HOME)))
            }
            //Response negative from the server if something went wrong about the token removal.
            is Failure -> handleTokenFailureResponse(res.value)
        }
    }

    @GetMapping(PathTemplate.Users.GET_BY_ID)
    fun getById(@PathVariable id: Int) : ResponseEntity<*> {
        return when(val res = userService.getUserById(id)) {
            is Success -> {
                val logoutLink = Link("logout", PathTemplate.Users.LOGOUT)
                return ResponseEntity.ok(
                    UserHomeOutputModel(
                        res.value.id,
                        res.value.name,
                        logoutLink
                    )
                )
            }
            is Failure -> handleUserFailureResponse(res.value)
        }
    }

    @GetMapping(PathTemplate.Users.HOME)
    fun getUserHome(userAuthenticatedUser: AuthenticatedUser): UserHomeOutputModel {
        val logoutLink = Link("logout", PathTemplate.Users.LOGOUT)
        return UserHomeOutputModel(
            id = userAuthenticatedUser.user.id,
            username = userAuthenticatedUser.user.name,
            logout = logoutLink
        )
    }

    private fun handleUserFailureResponse(error: UserError): ResponseEntity<*> {
        val errorResponse = when(error) {
            UserError.UserNotCreated -> Problem.response(500, Problem.userNotCreated)
            UserError.UserAlreadyExists -> Problem.response(400, Problem.userAlreadyExists)
            UserError.UserNotFound -> Problem.response(404, Problem.userNotFound)
            UserError.InsecurePassword -> Problem.response(400, Problem.insecurePassword)
        }
        return errorResponse
    }

    private fun handleTokenFailureResponse(error: TokenError): ResponseEntity<*> {
        val errorResponse = when(error) {
            TokenError.TokenNotCreated -> Problem.response(500, Problem.tokenNotCreated)
            TokenError.TokenInvalid -> Problem.response(400, Problem.tokenIsInvalid)
            TokenError.TokenNotFound -> Problem.response(404, Problem.tokenNotFound)
            TokenError.TokenNotUpdated -> Problem.response(500, Problem.tokenNotUpdated)
            TokenError.TokenOrUserNotValid -> Problem.response(400, Problem.tokenOrUserInvalid)
            TokenError.TokenNotRemoved -> Problem.response(500, Problem.tokenNotRemoved)
            TokenError.EmailOrPasswordAreInvalid -> Problem.response(400, Problem.tokenEmailOrPasswordAreInvalid)
        }
        return errorResponse
    }
}