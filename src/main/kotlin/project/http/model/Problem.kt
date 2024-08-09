package project.http.model

import org.springframework.http.ResponseEntity
import java.net.URI

class Problem(
    typeUri: URI
) {
    //Value that passed a URI to String.
    val type: String = typeUri.toASCIIString()

    companion object {
        //Value representing the type of error that can happen in this case will be json problem as a content-type.
        private const val MEDIA_TYPE = "application/problem+json"

        fun response(status: Int, problem: Problem): ResponseEntity<Any> = ResponseEntity
            .status(status)
            .header("Content-Type", MEDIA_TYPE)
            .body(problem)

        //Value representing the error when a user that wants to be created already exists.
        val userAlreadyExists = Problem(
            URI("https://example.com/probs/user-already-exists")
        )

        //Value representing the error when the user or the password are invalid.
        val userOrPasswordAreInvalid = Problem(
            URI("https://example.com/probs/user-or-password-are-invalid")
        )

        //Value representing when the password chosen is not secured.
        val insecurePassword = Problem(
            URI("https://example.com/probs/insecure-password")
        )

        //Value representing the error when the user was not found.
        val userNotFound = Problem(
            URI("https://example.com/probs/user-not-found")
        )

        //Value of the error when the token or user are invalid.
        val tokenOrUserInvalid = Problem(
            URI("https://example.com/probs/token-or-user-invalid")
        )

        //Value of the error when the token used was invalid.
        val tokenIsInvalid = Problem(
            URI("https://example.com/probs/invalid-token")
        )

        //Value of the error when the user was not created.
        val userNotCreated = Problem(
            URI("https://example.com/probs/user-not-created")
        )

        //Value of the error when the token was not created in the database.
        val tokenNotCreated = Problem(
            URI("https://example.com/probs/token-not-created")
        )

        //Value of the error when the token was not updated properly in the database.
        val tokenNotUpdated = Problem(
            URI("https://example.com/probs/token-not-updated")
        )

        //Value of the error when the token was not found in the database.
        val tokenNotFound = Problem(
            URI("https://example.com/probs/token-not-found")
        )

        //Value of the error when the token was not removed from the database.
        val tokenNotRemoved = Problem(
            URI("https://example.com/probs/token-not-removed")
        )

        //Value of the error when the email or password are invalid.
        val tokenEmailOrPasswordAreInvalid = Problem(
            URI("https://example.com/probs/email-or-password-are-invalid")
        )
    }
}
