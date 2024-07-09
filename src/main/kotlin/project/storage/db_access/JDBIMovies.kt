package project.storage.db_access

import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.kotlin.mapTo
import project.storage.repositories.MovieRepository
import project.domain.MovieDB


class JDBIMovies(private val handle: Handle) : MovieRepository {
    override fun addMovie(movieDB: MovieDB) : Int? {
        return handle.createUpdate(
            "INSERT INTO Movies (title, description, release_date, duration, rating) VALUES (:title, :description, :releaseDate, :duration, :rating)"
        )
            .bind("title", movieDB.title)
            .bind("description", movieDB.description)
            .bind("releaseDate", movieDB.releaseDate.epochSecond)
            .bind("duration", movieDB.duration.toInt())
            .bind("rating", movieDB.rating)
            .executeAndReturnGeneratedKeys()
            .mapTo<Int>()
            .singleOrNull()
    }

    override fun getMovieById(id: Int): MovieDB? {
        return handle.createQuery("SELECT * FROM Movies WHERE movie_id = :id")
            .bind("id", id)
            .mapTo<MovieDB>()
            .singleOrNull()
    }
}

