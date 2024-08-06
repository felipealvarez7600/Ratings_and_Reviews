package project.http

import org.springframework.web.util.UriTemplate
import java.net.URI

/**
 * Object that has all the paths possible for the application.
 */
object PathTemplate {
    //Values regarding the main pages of the application.
    const val PREFIX = "/api"
    //Value of the path to get the main home page.
    const val HOME = PREFIX
    //Value of the path to get the information of the dev plus the links to the documentation.
    const val SYSTEM = "$PREFIX/system-info"


    /**
     * Object with all the paths for the users in the application.
     */
    object Users {
        const val CREATE = "$PREFIX/account/create"
        const val LOGIN = "$PREFIX/account/login"
        const val LOGOUT = "$PREFIX/account/logout"
        const val HOME = "$PREFIX/home"
        const val GET_BY_ID = "$PREFIX/user/{id}"

    }

    /**
     * Object "Movies" with all the paths for the users related to the movies information in the application.
     */
    object Movies {
        const val MOVIE_BY_ID = "$PREFIX/games/{id}"


        fun movieById(id: Int): URI = UriTemplate(MOVIE_BY_ID).expand(id)

    }
}