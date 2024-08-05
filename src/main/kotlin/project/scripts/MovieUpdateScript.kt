package project.scripts

import org.jdbi.v3.core.Jdbi
import org.postgresql.ds.PGSimpleDataSource
import project.fetchData.FetchMovie
import project.repositories.jdbi.JdbiTransactionManager
import project.repositories.jdbi.utils.configureWithAppRequirements
import project.services.MovieError
import project.services.MovieServices
import project.utils.failure

class MovieUpdateScript {

    private val fetchMovie = FetchMovie()
    private val url = System.getenv("DB_Ratings_And_Reviews_URL")

    private fun jdbi(): Jdbi {
        val dataSource = PGSimpleDataSource().apply {
            setUrl(url)
        }

        return Jdbi.create(dataSource).configureWithAppRequirements()
    }

    private val transactionManager = JdbiTransactionManager(jdbi())

    private val maxUpdate = 10000

    fun updateMovies() = transactionManager.run(null) {
        var id = 1
        var countRows = 0
        while(id<maxUpdate) {
            val movie = fetchMovie.fetchMovieById(id++)
            if (movie != null) {
                val movieRows = it.moviesRepository.addMovie(movie)
                if (movieRows<1) {
                    it.rollback()
                    return@run failure(MovieError.MovieNotCreatedProperly)
                } else {
                    movie.genres.map { genre ->
                        it.movieShowMiscRepository.addGenre(genre)
                        it.moviesRepository.addGenreToMovie(genre.id, movie.id)
                    }
                    movie.productionCompanies.map { company ->
                        it.movieShowMiscRepository.addProductionCompany(company)
                        it.moviesRepository.addProductionCompanyToMovie(company.id, movie.id)
                    }
                    movie.productionCountries.map { country ->
                        it.movieShowMiscRepository.addProductionCountry(country)
                        it.moviesRepository.addProductionCountryToMovie(country.iso31661, movie.id)
                    }
                    movie.spokenLanguages.map { language ->
                        it.movieShowMiscRepository.addSpokenLanguage(language)
                        it.moviesRepository.addSpokenLanguageToMovie(language.iso6391, movie.id)
                    }
                    countRows++
                }
            }
        }
        return@run maxUpdate - countRows
    }


}