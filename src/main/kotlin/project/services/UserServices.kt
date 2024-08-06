package project.services

import kotlinx.datetime.Clock
import org.jdbi.v3.core.transaction.TransactionIsolationLevel
import org.springframework.stereotype.Component
import project.domain.users.Token
import project.domain.users.TokenExternalInfo
import project.domain.users.User
import project.domain.users.utils.UsersDomain
import project.repositories.TransactionManager
import project.utils.Either
import project.utils.failure
import project.utils.success



/**
 * Sealed class "UserError" that represents the errors able to happen when the user is created.
 */
sealed class UserError {
    //Object representing the error when the user already exists.
    data object UserAlreadyExists : UserError()
    //Object representing the error when the password is very weak.
    data object InsecurePassword : UserError()
    //Object representing the error when the user is not found.
    data object UserNotFound: UserError()
    //Object representing the error when the user is not created with success.
    data object UserNotCreated: UserError()
    //Object representing the error when the user is not added correctly to the database.
    data object UserNotAddedToRank: UserError()
}

//Type-Alias of the result of the creation of the user in this case using the object "Either" with the failure on the left "UserError" and success being an Int in this case the id of the user.
typealias UserResult = Either<UserError, Int>

/**
 * Sealed class "TokenCreationError" representing all the possible errors that can happen when creating a new token.
 */
sealed class TokenError {
    //Object representing when the Password or the user are invalid and in this case the creation of the token will be giving an error.
    data object EmailOrPasswordAreInvalid : TokenError()
    //Object representing when the Token that we are searching is invalid and in this case the search of the token will be giving an error.
    data object TokenInvalid: TokenError()
    //Object representing when the Token or User we are searching is invalid and in this case the search of the user or token will be giving an error.
    data object TokenOrUserNotValid: TokenError()
    //Object error representing when the Token was not created in the database.
    data object TokenNotCreated: TokenError()
    //Object error representing when the Token was not updated in the database.
    data object TokenNotUpdated: TokenError()
    //Object representing when the error of the Token was not found.
    data object TokenNotFound: TokenError()
    //Object representing when the error of the Token when he was not removed.
    data object TokenNotRemoved: TokenError()
}

//Type-alias of the result of the creation of the token in this case using the object "Either" with the failure on the left "TokenError" and success being the "TokenExternalInfo".
typealias TokenResult = Either<TokenError, TokenExternalInfo>

//Type-alias of the result of the search of the token in this case using the object "Either" with the failure on the left "TokenError" and success being the "User".
typealias TokenUserResult = Either<TokenError, User>

@Component
class UserService (private val transactionManager: TransactionManager,
                   private val usersDomain: UsersDomain,
                   private val clock: Clock
){
    /**
     * Function "createUser" responsible to create the user and insert it into the database.
     * @param username represents the name of the user.
     * @param email represents the email of the user.
     * @param password represents the password gave by the user.
     * @return UserResult represents the result of the creation of the user in our application.
     */
    fun createUser(username: String, email:String, password: String): UserResult {
        //Verification is the password gave by the new user is safe if it's not we will send a failure to notify the user about the issue.
        if (!usersDomain.isSafePassword(password)) {
            return failure(UserError.InsecurePassword)
        }
        //If the password is valid we will create the password but codified.
        val passwordValidationInfo = usersDomain.createPasswordValidationInformation(password)
        //The information to return will be the result of the transaction made.
        return transactionManager.run(TransactionIsolationLevel.SERIALIZABLE) {
            //Value to represent the information of the usersRepository.
            val usersRepository = it.usersRepository
            //If the user is using the same name then other user we must inform the user and send a failure message.
            if (usersRepository.getUserByEmail(email) != null) {
                return@run failure(UserError.UserAlreadyExists)
            } else {
                val newUser = User(
                    id = 0,
                    name = username,
                    email = email,
                    password = passwordValidationInfo,
                    createdAt = clock.now()
                )
                //If the user is valid the user will be stored in the database, and we will receive his id and verify.
                val id = usersRepository.storeUser(newUser) ?: return@run failure(UserError.UserNotCreated)
                //Success in the transaction and return to the user the id.
                success(id)
            }
        }
    }

    fun createToken(email: String, password: String): TokenResult {
        //Verification if the username is not blank and the password too if it is the transaction will fail it, so we inform the user about it.
        if (email.isBlank() || password.isBlank()) {
            return failure(TokenError.EmailOrPasswordAreInvalid)
        }
        //Return of the result of the transaction made to the database.
        return transactionManager.run(null) {
            //Value that represents the information of the usersRepository.
            val usersRepository = it.usersRepository
            //Value that represents the user with the name gave by him if he exists returns the information about him if not returns failure on the transaction.
            val user: User = usersRepository.getUserByEmail(email)
                ?: return@run failure(TokenError.EmailOrPasswordAreInvalid)
            //Verification if the password gave it is the password for that user and if not we return failure on the transaction.
            if (!usersDomain.validatePassword(password, user.password)) {
                return@run failure(TokenError.EmailOrPasswordAreInvalid)
            }


            //Value that will generate a token for the domain of the user.
            val tokenValue = usersDomain.generateTokenValue()
            //Value that will get the current time of that user.
            val now = clock.now()
            //Value that represents a new token for that user, and the time when created and when he was last used.
            val newToken = Token(
                usersDomain.createTokenValidationInformation(tokenValue),
                user.id,
                createdAt = now,
                lastUsedAt = now
            )
            //Creation of the token and update in the database regarding the token.
            val token = usersRepository.createToken(newToken)
            //Verification if the token was created with success.
            if(token != 1) return@run failure(TokenError.TokenNotCreated)
            //On the correct part of the answer we will put the information of the token in the response of the http request.
            success(TokenExternalInfo(tokenValue, usersDomain.getTokenExpiration(newToken)))
        }
    }

    /**
     * Function "getUserByToken" we will get the information regarding a user when it is provided the token.
     * @param token represents the token of the user.
     * @return TokenUserResult represents the result of the search of the token if he has an error we redirect to it if not return is the user information.
     */
    fun getUserByToken(token: String): TokenUserResult {
        //Verification if the token is Valid.
        if (!usersDomain.canBeToken(token)) {
            return failure(TokenError.TokenInvalid)
        }
        TODO()
//        //Transaction to make to the database.
//        return transactionManager.run(null) {
//            //Value representing the usersRepository.
//            val usersRepository = it.usersRepository
//            //Value to create for the user a token valid for that token.
//            val tokenValidationInfo = usersDomain.createTokenValidationInformation(token)
//            //Value that will get the user and the token in a pair with the information.
//            val userAndToken = usersRepository.getUserByToken(tokenValidationInfo.validationInfo)
//            //Verification of the information in user and token and if they are valid we return success if not we send a failure information.
//            if (userAndToken != null && usersDomain.isTokenTimeValid(clock, Token())) {
//                //Update in the database of the token last time used.
//                val updateUser = usersRepository.updateTokenLastUsed(userAndToken.second, clock.now())
//                //Verification if the token was updated.
//                if(updateUser != 1) return@run failure(TokenError.TokenNotUpdated)
//                //Return of user with the token we searched.
//                success(userAndToken.first)
//            } else {
//                //Return of a error message if an error occurred.
//                failure(TokenError.TokenOrUserNotValid)
//            }
//        }
    }
}