package project.services

import kotlinx.datetime.Clock
import org.springframework.stereotype.Component
import project.domain.movies.Movie
import project.domain.movies.TitleMovie
import project.fetchData.FetchMovie
import project.repositories.TransactionManager
import project.utils.Either
import project.utils.failure
import project.utils.success

sealed class MovieError {
    data object MovieNotFound : MovieError()
    data object MovieBadParams : MovieError()
    data object MovieNotAvailable : MovieError()
    data object MovieNotCreatedProperly : MovieError()
    data object GenreNotCreatedProperly : MovieError()
    data object ProductionCompanyNotCreatedProperly : MovieError()
    data object ProductionCountryNotCreatedProperly : MovieError()
    data object SpokenLanguageNotCreatedProperly : MovieError()
}

typealias MovieResult = Either<MovieError, Movie>
typealias MovieListResult = Either<MovieError, List<TitleMovie>>
typealias MovieUpdateResult = Either<MovieError, Boolean>


@Component
class MovieServices (
    private val transactionManager: TransactionManager,
    private val clock: Clock
) {
    private val fetchMovie = FetchMovie()

    fun getMovieById(id: Int) : MovieResult =
        transactionManager.run(null) {
            val movieDB = it.moviesRepository.getMovieById(id)
            if(movieDB != null) {
                return@run success(movieDB)
            }
            val movie = fetchMovie.fetchMovieById(id)
            if (movie != null) {
                val rows = it.moviesRepository.addMovie(movie)
                if (rows<1) {
                    return@run failure(MovieError.MovieNotCreatedProperly)
                }
                success(movie)
            } else {
                return@run failure(MovieError.MovieNotFound)
            }
        }

    fun getMoviesByTitleFetch(title: String) : List<TitleMovie> {
        val movies = fetchMovie.fetchMoviesByTitle(title)
        return movies ?: emptyList()
    }

    fun getMoviesByTitleDB(title: String, limit:Int, skip: Int) : MovieListResult {
        if(limit < 0 || skip < 0) {
            return failure(MovieError.MovieBadParams)
        }
        val movies = transactionManager.run(null) {
            it.moviesRepository.getMoviesByTitle(title, limit, skip)
        }
        return success(movies)
    }
}










