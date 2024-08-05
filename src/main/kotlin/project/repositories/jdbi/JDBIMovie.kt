package project.repositories.jdbi

import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.kotlin.mapTo
import project.repositories.MovieRepository
import project.domain.movies.Movie
import project.domain.movies.TitleMovie


class JDBIMovie(private val handle: Handle) : MovieRepository {
    override fun addMovie(movie: Movie) : Int {
        return handle.createUpdate(
            "INSERT INTO Movies VALUES (" +
                    ":original_language, :imdb_id, :video, :title, :backdrop_path, :revenue, " +
                    ":popularity, :id, :vote_count, :budget, :overview, :original_title, " +
                    ":runtime, :poster_path, :release_date, :vote_average, :belongs_to_collection, " +
                    ":tagline, :adult, :homepage, :status)" +
                    "ON CONFLICT (id) DO UPDATE SET " +
                    "status = EXCLUDED.status, revenue = EXCLUDED.revenue, popularity = EXCLUDED.popularity, " +
                    "vote_count = EXCLUDED.vote_count, vote_average = EXCLUDED.vote_average"
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

    override fun addGenreToMovie(genreId: Int, movieId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO movie_genres (genre_id, movie_id) " +
                    "VALUES (:genreId, :movieId) ON CONFLICT (genre_id, movie_id) DO NOTHING"
        )
            .bind("genreId", genreId)
            .bind("movieId", movieId)
            .execute()
    }

    override fun addProductionCompanyToMovie(companyId: Int, movieId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO movie_production_companies (production_company_id, movie_id) " +
                    "VALUES (:companyId, :movieId) ON CONFLICT (production_company_id, movie_id) DO NOTHING"
        )
            .bind("companyId", companyId)
            .bind("movieId", movieId)
            .execute()
    }

    override fun addProductionCountryToMovie(countryId: String, movieId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO movie_production_countries (production_country_id, movie_id) " +
                    "VALUES (:countryId, :movieId) ON CONFLICT (production_country_id, movie_id) DO NOTHING"
        )
            .bind("countryId", countryId)
            .bind("movieId", movieId)
            .execute()
    }

    override fun addSpokenLanguageToMovie(languageId: String, movieId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO movie_spoken_languages (spoken_language_id, movie_id) " +
                    "VALUES (:languageId, :movieId) ON CONFLICT (spoken_language_id, movie_id) DO NOTHING"
        )
            .bind("languageId", languageId)
            .bind("movieId", movieId)
            .execute()
    }


    override fun getMovieById(id: Int): Movie? {
        return handle.createQuery("SELECT * FROM Movies WHERE id = :id LIMIT 1")
            .bind("id", id)
            .mapTo<Movie>()
            .singleOrNull()
    }

    override fun getMoviesByTitle(title: String, limit: Int, skip: Int): List<TitleMovie> {
        return handle.createQuery("SELECT * FROM Movies WHERE title = :title LIMIT :limit OFFSET :skip")
            .bind("title", title)
            .bind("limit", limit)
            .bind("skip", skip)
            .mapTo<TitleMovie>()
            .list()
    }
}

