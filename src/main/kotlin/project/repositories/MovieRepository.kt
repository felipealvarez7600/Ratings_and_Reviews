package project.repositories

import project.domain.movies.Movie
import project.domain.movies.TitleMovie

interface MovieRepository {
    fun addMovie(movie: Movie): Int
    fun addGenreToMovie(genreId: Int, movieId: Int): Int
    fun addProductionCompanyToMovie(companyId: Int, movieId: Int): Int
    fun addProductionCountryToMovie(countryId: String, movieId: Int): Int
    fun addSpokenLanguageToMovie(languageId: String, movieId: Int): Int
    fun getMovieById(id: Int): Movie?
    fun getMoviesByTitle(title: String, limit:Int, skip:Int): List<TitleMovie>
}