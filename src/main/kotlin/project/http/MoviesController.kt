package project.http

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import project.domain.users.AuthenticatedUser
import project.services.MovieServices

@RestController
class MoviesController (private val moviesService: MovieServices){
    @GetMapping(PathTemplate.Movies.MOVIE_BY_ID)
    fun getMovies(authenticatedUser: AuthenticatedUser, @PathVariable id: Int) : ResponseEntity<*> {
        TODO()
    }

}