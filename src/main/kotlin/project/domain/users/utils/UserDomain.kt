package project.domain.users.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import project.domain.users.Password
import project.domain.users.Token
import project.domain.users.TokenValidationInfo
import java.security.SecureRandom
import java.util.*

@Component
class UsersDomain (
    val passwordEncoder: PasswordEncoder,
    val tokenEncoder: TokenEncoder,
    val config: UsersDomainConfig
){
    /**
     * Function "generateTokenValue" responsible to generate the value for the token.
     * @return String represents the token.
     */
    fun generateTokenValue(): String =
        ByteArray(config.tokenSizeInBytes).let { byteArray ->
            SecureRandom.getInstanceStrong().nextBytes(byteArray)
            Base64.getUrlEncoder().encodeToString(byteArray)
        }

    /**
     * Function "canBeToken" used to verify if the token can be used as a token or not.
     * @param token represents the token to evaluate.
     * @return Boolean represents the result if can or not be a token.
     */
    fun canBeToken(token: String): Boolean = try {
        Base64.getUrlDecoder()
            .decode(token).size == config.tokenSizeInBytes
    } catch (ex: IllegalArgumentException) {
        false
    }

    /**
     * Function "validatePassword" responsible to verify if a password can be used as valid or not after the encoding.
     * @param password represents the password gave by the user.
     * @param validationInfo represents the validation information about it.
     * @return Boolean represents if the password matches to the validationInfo or not.
     */
    fun validatePassword(password: String, validationInfo: Password) = passwordEncoder.matches(
        password,
        validationInfo.hashedPassword
    )

    /**
     * Function "createPasswordValidationInformation" to create a password encoded.
     * @param password represents the original password.
     * @return PasswordValidationInfo represents the password encoded.
     */
    fun createPasswordValidationInformation(password: String) = Password(
        hashedPassword = passwordEncoder.encode(password)
    )

    /**
     * Function "isTokenTimeValid" responsible to verify if the token is not expired or is.
     * @param clock represents the current time of the server.
     * @param token represents the token to evaluate.
     * @return Boolean represents if it is valid or not.
     */
    fun isTokenTimeValid(clock: Clock, token: Token): Boolean {
        val now = clock.now()
        return token.createdAt <= now &&
                (now - token.createdAt) <= config.tokenTtl &&
                (now - token.lastUsedAt) <= config.tokenRollingTtl
    }

    /**
     * Function "getTokenExpiration" responsible to get the time the token will be expired.
     * @param token represents the token we want to evaluate.
     * @return Instant representing the time for him to expire.
     */
    fun getTokenExpiration(token: Token): Instant {
        //Value representing the time when he is expired.
        val absoluteExpiration = token.createdAt + config.tokenTtl
        //Value representing the time left to expire.
        val rollingExpiration = token.lastUsedAt + config.tokenRollingTtl
        //Verification if the token is not expire, or it is and returning the correct time.
        return if (absoluteExpiration < rollingExpiration) {
            absoluteExpiration
        } else {
            rollingExpiration
        }
    }

    /**
     * Function "createTokenValidationInformation" represents the Token Validation information in this case we will encode the token.
     * @param token represent the token we want to encode.
     * @return TokenValidationInfo represents the token information encoded.
     */
    fun createTokenValidationInformation(token: String): TokenValidationInfo =
        tokenEncoder.createValidationInformation(token)

    /**
     * Function "isSafePassword" responsible to verify if the password chosen is safe or not.
     * @param password representing the password to evaluate.
     * @return Boolean represents the result if the password is safe passing the evaluation sentences to determine if it is safe enough.
     */
    fun isSafePassword(password: String) : Boolean =
        password.length > 5 &&
                password.any { it.isDigit() } &&
                password.any { !it.isLetterOrDigit() } &&
                password.any { it.isLowerCase() } &&
                password.any { it.isUpperCase() }


}