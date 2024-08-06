package project.domain.users.utils

import project.domain.users.TokenValidationInfo

interface TokenEncoder {
    /**
     * Function "createValidationInformation" responsible to encode the Token.
     * @param token represents the token.
     * @return TokenValidationInfo represents the Token information already encoded.
     */
    fun createValidationInformation(token: String): TokenValidationInfo
}