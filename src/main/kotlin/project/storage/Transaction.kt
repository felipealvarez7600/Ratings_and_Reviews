package project.storage

import project.storage.repositories.MovieRepository

/**
 * Interface Transaction used to assist in the transactions.
 */
interface Transaction {
    //Users repository.
    val movieRepository: MovieRepository

    //Function to use when an error happen to roll back the information to a solid state.
    fun rollback()
}