package project.storage.repositories

import project.domain.MovieDB

interface MovieRepository {
    fun addMovie(movieDB: MovieDB): Int?
    fun getMovieById(id: Int): MovieDB?
}