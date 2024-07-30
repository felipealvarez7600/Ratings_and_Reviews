package project.storage.repositories

import project.domain.movies.Movie

interface MovieRepository {
    fun addMovie(movie: Movie): Int?
    fun getMovieById(id: Int): Movie?
    fun getMovieByTitle(title: String): Movie?
}