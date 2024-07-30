package project.repositories.jdbi

import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.kotlin.mapTo
import project.repositories.MovieRepository
import project.domain.movies.Movie


class JDBIMovies(private val handle: Handle) : MovieRepository {
    override fun addMovie(movie: Movie) : Int {
        return handle.createUpdate(
            "INSERT INTO Movies VALUES (" +
                    ":original_language, :imdb_id, :video, :title, :backdrop_path, :revenue, " +
                    ":popularity, :id, :vote_count, :budget, :overview, :original_title, " +
                    ":runtime, :poster_path, :release_date, :vote_average, :belongs_to_collection, " +
                    ":tagline, :adult, :homepage, :status)"
        )
            .bind("original_language", movie.originalLanguage)
            .bind("imdb_id", movie.imdbId)
            .bind("video", movie.video)
            .bind("title", movie.title)
            .bind("backdrop_path", movie.backdropPath)
            .bind("revenue", movie.revenue)
            .bind("popularity", movie.popularity)
            .bind("id", movie.id)
            .bind("vote_count", movie.voteCount)
            .bind("budget", movie.budget)
            .bind("overview", movie.overview)
            .bind("original_title", movie.originalTitle)
            .bind("runtime", movie.runtime)
            .bind("poster_path", movie.posterPath)
            .bind("release_date", movie.releaseDate)
            .bind("vote_average", movie.voteAverage)
            .bind("belongs_to_collection", movie.belongsToCollection)
            .bind("tagline", movie.tagline)
            .bind("adult", movie.adult)
            .bind("homepage", movie.homepage)
            .bind("status", movie.status)
            .execute()
    }

    override fun getMovieById(id: Int): Movie? {
        return handle.createQuery("SELECT * FROM Movies WHERE id = :id")
            .bind("id", id)
            .mapTo<Movie>()
            .singleOrNull()
    }

    override fun getMovieByTitle(title: String): Movie? {
        return handle.createQuery("SELECT * FROM Movies WHERE title = :title")
            .bind("title", title)
            .mapTo<Movie>()
            .singleOrNull()
    }
}

